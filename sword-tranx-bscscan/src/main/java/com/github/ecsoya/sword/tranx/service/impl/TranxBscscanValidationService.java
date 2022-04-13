package com.github.ecsoya.sword.tranx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ecsoya.sword.tranx.domain.TranxBase;
import com.github.ecsoya.sword.tranx.service.ITranxBscscanService;
import com.github.ecsoya.sword.tranx.service.ITranxScanService;
import com.github.ecsoya.sword.tranx.service.ITranxValidationService;

@Service(ITranxValidationService.BSCSCAN)
public class TranxBscscanValidationService extends AbstractValidationServiceImpl implements ITranxValidationService {

	@Autowired
	private ITranxBscscanService bscscanService;

	@Override
	protected TranxBase getTranxByHash(String txHash, String key) {
		return bscscanService.selectTranxBscscanById(txHash);
	}

	@Override
	protected String getToken() {
		return ITranxScanService.TOKEN_BNB;
	}
}
