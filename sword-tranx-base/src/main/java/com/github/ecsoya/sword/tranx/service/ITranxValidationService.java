package com.github.ecsoya.sword.tranx.service;

import java.math.BigDecimal;

import com.github.ecsoya.sword.tranx.domain.TranxValidation;

public interface ITranxValidationService {

	public String BSCSCAN = "bscscanValidation";
	public String TRONSCAN = "tronscanValidation";

	/**
	 * Validate the txHash with given price and value.
	 * 
	 * @param key    币种
	 * @param txHash 交易哈希
	 * @param value  验证金额
	 * @param bounce 允许误差
	 * @return result
	 */
	public TranxValidation validateTransferIn(String key, String txHash, BigDecimal value, BigDecimal bounce);

	/**
	 * Validate the txHash with given price and value.
	 * 
	 * @param key    币种
	 * @param txHash 交易哈希
	 * @param value  验证金额
	 * @param bounce 允许误差
	 * @return result
	 */
	public TranxValidation validateTransferInSafely(String key, String txHash, String fromAddress, BigDecimal value,
			BigDecimal bounce);
}
