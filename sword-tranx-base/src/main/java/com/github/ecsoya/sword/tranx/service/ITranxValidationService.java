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
	 * @param scale  允许误差
	 * @return result
	 */
	public TranxValidation validateTransferIn(String key, String txHash, BigDecimal value, Integer scale);

	/**
	 * Validate the txHash with given price and value.
	 * 
	 * @param key    币种
	 * @param txHash 交易哈希
	 * @param value  验证金额
	 * @param scale  允许误差
	 * @return result
	 */
	public TranxValidation validateTransferInSafely(String key, String txHash, String fromAddress, BigDecimal value,
			Integer scale);

	/**
	 * Validate transfer.
	 *
	 * @param txHash      the tx hash
	 * @param symbol TODO
	 * @param fromAddress the from address
	 * @param toAddress   the to address
	 * @param value       the value
	 * @param scale       the scale
	 * @return the tranx validation
	 */
	public TranxValidation validateTransfer(String txHash, String symbol, String fromAddress, String toAddress,
			BigDecimal value, Integer scale);
}
