package com.github.ecsoya.sword.tranx.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.ecsoya.sword.tranx.config.BscscanConfig;
import com.github.ecsoya.sword.tranx.domain.Bscscan;
import com.github.ecsoya.sword.tranx.domain.TranxBase;
import com.github.ecsoya.sword.tranx.domain.TranxBscscan;
import com.github.ecsoya.sword.tranx.domain.TranxSymbol;
import com.github.ecsoya.sword.tranx.service.ITranxBscscanService;
import com.github.ecsoya.sword.tranx.service.ITranxScanService;
import com.github.ecsoya.sword.tranx.service.ITranxSymbolService;
import com.github.ecsoya.sword.tranx.util.HttpClientUtil;

@Service(ITranxScanService.BSCSCAN)
public class TranxBscscanPrimaryServiceImpl implements ITranxScanService {

	private static final Logger log = LoggerFactory.getLogger(ITranxScanService.BSCSCAN);

	private static final Long BLOCK_FAILED = -1l;

	private static final Integer BNB_DECIMALS = 18;

	@Autowired
	private BscscanConfig config;

	@Autowired
	private ITranxSymbolService symbolService;

	@Autowired
	private ITranxBscscanService tranxBscscanService;

	@Override
	public int scanTranx(TranxSymbol symbol) {
		if (symbol == null || !Objects.equals(TOKEN_BNB, symbol.getToken())) {
			return 0;
		}
		log.info("BscScan: {}", TOKEN_BNB);
		String baseUrl = config.getBaseUrl();
		String[] apiKeys = config.getApiKeys();
		String action = config.getAction();
		return loadTranx(baseUrl, apiKeys, action, symbol);
	}

	private int loadTranx(String baseUrl, String[] apiKeys, String action, TranxSymbol symbol) {
		Long blockNumber = symbol.getBlockNumber();
		for (String apiKey : apiKeys) {
			Long newBlockNumber = loadTranx(blockNumber, baseUrl, apiKey, action, symbol);
			if (BLOCK_FAILED.equals(newBlockNumber)) {
				continue;
			} else if (newBlockNumber == null) {
				break;
			} else if (!newBlockNumber.equals(blockNumber)) {
				blockNumber = newBlockNumber;
			}
		}
		TranxSymbol update = new TranxSymbol();
		update.setSymbol(symbol.getSymbol());
		update.setBlockNumber(blockNumber);
		return symbolService.updateTranxSymbol(update);
	}

	private Long loadTranx(Long blockNumber, String baseUrl, String apiKey, String action, TranxSymbol symbol) {
		if (symbol == null) {
			return null;
		}
		String address = symbol.getAddress();
		Map<String, String> params = new HashMap<>();
		params.put("module", "account");
		if (action != null && !action.equals("")) {
			params.put("action", action);
		} else {
			params.put("action", "tokentx"); // tokentx
		}
		params.put("address", address);
		if (blockNumber != null) {
			params.put("startblock", blockNumber.toString());
		}
		params.put("apikey", apiKey);
		Integer confirms = symbol.getConfirms();
		try {
			String json = HttpClientUtil.doGet(baseUrl, params);
			log.info("BscScan: {}", json);
			Bscscan res = Bscscan.parse(json);
			if (res != null && res.isOk()) {
				TranxBscscan[] result = res.getResult();
				if (result != null) {
					Long block = null;
					for (TranxBscscan tranx : result) {
						if (symbol != null) {
							tranx.setToken(symbol.getToken());
						} else {
							tranx.setToken(TOKEN_BNB);
							tranx.setDecimals(BNB_DECIMALS);
						}
						if (confirms != null) {
							Integer myConfirm = tranx.getConfirmations();
							if (myConfirm == null || myConfirm < confirms) {
								return null;
							}
						}
						tranxBscscanService.insertTranxBscscan(tranx);
						if (block == null) {
							block = tranx.getBlockNumber();
						} else {
							block = Math.max(block, tranx.getBlockNumber());
						}
					}
					return block;
				}
			}
		} catch (Exception e) {
			log.error("BscScan error", e);
			return BLOCK_FAILED;
		}
		return null;
	}

	@Override
	public TranxBase getTranxByHash(String hash, String symbolKey) {
		TranxBscscan tranx = tranxBscscanService.selectTranxBscscanById(hash);
		if (tranx != null) {
			return tranx;
		}
		return tranx;
	}

	@Override
	public List<TranxBase> loadTranxByHash(String hash, Integer confirmations, String... addresses) {
		if (StringUtils.isEmpty(hash) || addresses == null || addresses.length == 0) {
			return Collections.emptyList();
		}
		List<TranxBase> tranxs = new ArrayList<>();
		String baseUrl = config.getBaseUrl();
		String[] apiKeys = config.getApiKeys();
		String action = config.getAction();
		String contractAddress = config.getContractAddress();
		Arrays.asList(addresses).forEach(address -> {
			if (!tranxs.isEmpty()) {
				return;
			}
			Map<String, String> params = new HashMap<>();
			params.put("module", "account");
			if (action != null && !action.equals("")) {
				params.put("action", action);
			} else {
				params.put("action", "tokentx"); // tokentx
			}
			params.put("address", address);
			params.put("sort", "desc");
			params.put("apikey", apiKeys[0]);
			if (contractAddress != null) {
				params.put("contractaddress", contractAddress);
			}
			Integer confirms = confirmations;
			try {
				String json = HttpClientUtil.doGet(baseUrl, params);
				log.info("BscScan: {}", json);
				Bscscan res = Bscscan.parse(json);
				if (res != null && res.isOk()) {
					TranxBscscan[] result = res.getResult();
					if (result != null) {
						for (TranxBscscan tranx : result) {
							if (confirms != null && tranx.getConfirmations() != null
									&& confirms > tranx.getConfirmations()) {
								continue;
							}
							if (Objects.equals(hash, tranx.getHash())) {
								tranxs.add(tranx);
								break;
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("BscScan error", e);
			}
		});
		return tranxs;
	}
}
