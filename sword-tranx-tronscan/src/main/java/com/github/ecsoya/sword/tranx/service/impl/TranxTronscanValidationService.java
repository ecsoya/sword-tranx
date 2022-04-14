package com.github.ecsoya.sword.tranx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.ecsoya.sword.tranx.domain.TranxBase;
import com.github.ecsoya.sword.tranx.service.ITranxScanService;
import com.github.ecsoya.sword.tranx.service.ITranxValidationService;

@Service(ITranxValidationService.TRONSCAN)
public class TranxTronscanValidationService extends AbstractValidationServiceImpl implements ITranxValidationService {

	@Resource(name = ITranxScanService.TRONSCAN)
	private ITranxScanService tranxScanService;

	@Override
	protected String getToken() {
		return ITranxScanService.TOKEN_TRX;
	}
	
	@Override
	protected ITranxScanService getTranxScanService() {
		return tranxScanService;
	}
}
