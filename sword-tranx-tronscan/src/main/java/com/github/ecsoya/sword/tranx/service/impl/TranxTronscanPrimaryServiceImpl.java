package com.github.ecsoya.sword.tranx.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ecsoya.sword.tranx.config.TronscanConfig;
import com.github.ecsoya.sword.tranx.domain.TranxSymbol;
import com.github.ecsoya.sword.tranx.domain.TranxTronscan;
import com.github.ecsoya.sword.tranx.domain.Tronscan;
import com.github.ecsoya.sword.tranx.service.ITranxScanService;
import com.github.ecsoya.sword.tranx.service.ITranxSymbolService;
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

	@Override
	public int scanTranx(TranxSymbol symbol) {
		if (symbol == null || !Objects.equals(TOKEN_TRX, symbol.getToken())) {
			return 0;
		}
		log.info("TronScan: {}", TOKEN_TRX);
		String baseUrl = config.getBaseUrl();
		String path = config.getPath();
		return loadTranx(baseUrl, path, symbol);
	}

	private int loadTranx(String baseUrl, String path, TranxSymbol symbol) {
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
				symbolService.updateTranxSymbol(symbol);
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

}
