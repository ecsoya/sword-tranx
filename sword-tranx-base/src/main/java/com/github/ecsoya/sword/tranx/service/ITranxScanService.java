package com.github.ecsoya.sword.tranx.service;

import java.util.List;

import com.github.ecsoya.sword.tranx.domain.TranxBase;
import com.github.ecsoya.sword.tranx.domain.TranxSymbol;

public interface ITranxScanService {
	public String BSCSCAN = "bscscanService";
	public String TRONSCAN = "tronscanService";

	public String TOKEN_TRX = "trx";
	public String TOKEN_BNB = "bnb";

	public int scanTranx(TranxSymbol symbol);

	public TranxBase getTranxByHash(String hash, String symbolKey);

	public List<TranxBase> loadTranxByHash(String hash, Integer confirmations, String... addresses);
}
