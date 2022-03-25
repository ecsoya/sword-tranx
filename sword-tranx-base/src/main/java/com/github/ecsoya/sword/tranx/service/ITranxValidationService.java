package com.github.ecsoya.sword.tranx.service;

import java.math.BigDecimal;

import com.github.ecsoya.sword.tranx.domain.TranxValidation;

public interface ITranxValidationService {

	public String BSCSCAN = "bscscanValidation";
	public String TRONSCAN = "tronscanValidation";

	/**
	 * Validate the txHash with given price and value.
	 * 
	 * @param symbol
	 * @param txHash
	 * @param price
	 * @param value
	 * @return result
	 */
	public TranxValidation validateTranx(String symbol, String txHash, BigDecimal price, BigDecimal value);
}
