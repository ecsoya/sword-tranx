package com.github.ecsoya.sword.tranx.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.alibaba.fastjson.annotation.JSONField;

public class TranxBase implements Serializable {

	private static final long serialVersionUID = -7339239775043051813L;

	/** TxID */
	private String hash;

	/** 代币 */
	private String symbol;

	/** 精度 */
	private Integer decimals;

	/** FORM */
	@JSONField(alternateNames = { "from_address", "from" })
	private String fromAddress;

	/** TO */
	@JSONField(alternateNames = { "to_address", "to" })
	private String toAddress;

	/** Value */
	@JSONField(alternateNames = { "amount", "value" })
	private Long amount;

	/** 合约地址 */
	@JSONField(alternateNames = "contract_address")
	private String contractAddress;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Integer getDecimals() {
		return decimals;
	}

	public void setDecimals(Integer decimals) {
		this.decimals = decimals;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public BigDecimal getRealValue() {
		if (amount == null) {
			return null;
		}
		BigDecimal pow = BigDecimal.ONE;
		if (decimals != null) {
			pow = BigDecimal.valueOf(10).pow(decimals.intValue());
		}
		return BigDecimal.valueOf(amount).divide(pow, 6, RoundingMode.HALF_UP);
	}
}
