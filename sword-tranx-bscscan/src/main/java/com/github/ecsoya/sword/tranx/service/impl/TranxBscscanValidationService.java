package com.github.ecsoya.sword.tranx.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.github.ecsoya.sword.tranx.domain.TranxValidation;
import com.github.ecsoya.sword.tranx.service.ITranxValidationService;

@Service(ITranxValidationService.BSCSCAN)
public class TranxBscscanValidationService implements ITranxValidationService {

	@Override
	public TranxValidation validateTranx(String symbol, String txHash, BigDecimal price, BigDecimal value) {
		return TranxValidation.error("Not implemented");
	}

}
