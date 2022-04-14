package com.github.ecsoya.sword.tranx.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.github.ecsoya.sword.tranx.config.TronscanConfig;
import com.github.ecsoya.sword.tranx.domain.TranxBase;
import com.github.ecsoya.sword.tranx.domain.TranxSymbol;
import com.github.ecsoya.sword.tranx.domain.TranxTrc20;
import com.github.ecsoya.sword.tranx.domain.TranxTronscan;
import com.github.ecsoya.sword.tranx.domain.TranxTrx;
import com.github.ecsoya.sword.tranx.domain.Tronscan;
import com.github.ecsoya.sword.tranx.service.ITranxScanService;
import com.github.ecsoya.sword.tranx.service.ITranxSymbolService;
import com.github.ecsoya.sword.tranx.service.ITranxTrc20Service;
import com.github.ecsoya.sword.tranx.service.ITranxTronscanService;
import com.github.ecsoya.sword.tranx.util.HttpClientUtil;

@Service(ITranxScanService.TRONSCAN)
public class TranxTronscanPrimaryServiceImpl implements ITranxScanService {
	private static final Logger log = LoggerFactory.getLogger(ITranxScanService.TRONSCAN);

	private static final String TOKEN_TRX = "trx";

	private static final Long BLOCK_FAILED = -1l;

	private static final Integer TRX_DECIMALS = 6;
	private static final int PAGE_LIMIT = 10;

	@Autowired
	private TronscanConfig config;

	@Autowired
	private ITranxSymbolService symbolService;

	@Autowired
	private ITranxTronscanService tranxTronscanService;

	@Autowired
	private ITranxTrc20Service tranxTrc20Service;

	@Override
	public int scanTranx(TranxSymbol symbol) {
		if (symbol == null || !Objects.equals(TOKEN_TRX, symbol.getToken())) {
			return 0;
		}
		log.info("TronScan: {}", TOKEN_TRX);
		String baseUrl = config.getBaseUrl();
		String path = config.getTransaction();
		log.info("TronScan: {}", baseUrl + path);
		Long start = symbol.getBlockNumber();
		int maxTries = 0;
		while (true) {
			Long loaded = loadTranx(start, baseUrl, path, symbol);
			if (BLOCK_FAILED.equals(loaded)) {
				maxTries++;
				continue;
			} else if (loaded == null || loaded == 0l) {
				break;
			} else {
				start += loaded;
				TranxSymbol update = new TranxSymbol();
				update.setSymbol(symbol.getSymbol());
				update.setBlockNumber(start);
				symbolService.updateTranxSymbol(update);
			}

			if (maxTries > 10) {
				break;
			}
		}
		return 1;
	}

	private Long loadTranx(Long start, String baseUrl, String path, TranxSymbol symbol) {
		if (symbol == null) {
			return null;
		}
		String address = symbol.getAddress();
		Map<String, String> params = new HashMap<>();
		params.put("count", "true");
		params.put("limit", Integer.toString(PAGE_LIMIT));
		params.put("start", start.toString());
		params.put("address", address);
		try {
			String json = HttpClientUtil.doGet(baseUrl + path, params);
			log.info("TronScan {}", json);
			Tronscan res = Tronscan.parse(json);
			if (res != null) {
				TranxTronscan[] result = res.getData();
				if (result != null) {
					for (TranxTronscan tranx : result) {
						if (symbol != null) {
							tranx.setSymbol(symbol.getSymbol());
							tranx.setToken(symbol.getToken());
							tranx.setDecimals(symbol.getDecimals());
						} else {
							tranx.setToken(TOKEN_TRX);
							tranx.setDecimals(TRX_DECIMALS);
						}
						tranxTronscanService.insertTranxTronscan(tranx);

						// if (Objects.equals(symbol.getAddress(), tranx.getToAddress())) {
						loadTrc20(tranx.getHash(), symbol);
						// }
					}
				}
				return Long.valueOf(result.length);
			}
			return null;
		} catch (Exception e) {
			log.error("TronScan error", e);
			return BLOCK_FAILED;
		}
	}

	private void loadTrc20(String hash, TranxSymbol symbol) {
		log.info("TrxTranx: {}", hash);
		Integer confirms = symbol != null ? symbol.getConfirms() : null;
		loadTranxByHash(hash, confirms).forEach(transfer -> {
			if (transfer instanceof TranxTrc20) {
				tranxTrc20Service.insertTranxTrc20((TranxTrc20) transfer);
			}
		});
	}

	@Override
	public TranxBase getTranxByHash(String hash, String symbolKey) {
		if (hash == null || hash.equals("")) {
			return null;
		}
		return tranxTrc20Service.selectTranxTrc20ById(hash);
	}

	@Override
	public List<TranxBase> loadTranxByHash(String hash, Integer confirmations, String... addresses) {
		if (StringUtils.isEmpty(hash)) {
			return Collections.emptyList();
		}
		log.info("TrxTranx: {}", hash);
		try {
			List<String> filterAddresses = addresses != null ? Arrays.asList(addresses) : Collections.emptyList();
			String baseUrl = config.getBaseUrl();
			String path = config.getTransactionInfo();
			Map<String, String> params = new HashMap<>();
			params.put("hash", hash);
			String json = HttpClientUtil.doGet(baseUrl + path, params);
			log.info("TrxTranx: {}", json);
			TranxTrx trx = JSON.parseObject(json, TranxTrx.class);
			if (trx != null && trx.getTrc20TransferInfo() != null) {
				if (confirmations != null) {
					Integer myConfirmations = trx.getConfirmations();
					if (myConfirmations == null || myConfirmations < confirmations) {
						return Collections.emptyList();
					}
				}
				List<TranxBase> result = new ArrayList<>();
				TranxTrc20[] transfers = trx.getTrc20TransferInfo();
				for (TranxTrc20 transfer : transfers) {
					transfer.setHash(hash);
					if (containsAddress(filterAddresses, transfer.getFromAddress())
							|| containsAddress(filterAddresses, transfer.getToAddress())) {
						result.add(transfer);
					}
				}
				return result;
			}
		} catch (Exception e) {
			log.error("TrxTranx error", e);
		}
		return Collections.emptyList();
	}

	private boolean containsAddress(List<String> filterAddresses, String address) {
		if (filterAddresses == null || filterAddresses.isEmpty()) {
			return true;
		}
		if (address == null) {
			return false;
		}
		List<String> addresses = filterAddresses.parallelStream().map(a -> a.toLowerCase())
				.collect(Collectors.toList());
		return addresses.contains(address.toLowerCase());
	}
}
