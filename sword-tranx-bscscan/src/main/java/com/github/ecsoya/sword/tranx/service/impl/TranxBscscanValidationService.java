package com.github.ecsoya.sword.tranx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.ecsoya.sword.tranx.service.ITranxScanService;
import com.github.ecsoya.sword.tranx.service.ITranxValidationService;

@Service(ITranxValidationService.BSCSCAN)
public class TranxBscscanValidationService extends AbstractValidationServiceImpl implements ITranxValidationService {

	@Resource(name = ITranxScanService.BSCSCAN)
	private ITranxScanService bscscanService;

	@Override
	protected ITranxScanService getTranxScanService() {
		return bscscanService;
	}

	@Override
	protected String getToken() {
		return ITranxScanService.TOKEN_BNB;
	}
}
