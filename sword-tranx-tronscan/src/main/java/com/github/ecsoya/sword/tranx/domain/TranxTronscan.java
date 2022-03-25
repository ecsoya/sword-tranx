package com.github.ecsoya.sword.tranx.domain;

import java.io.Serializable;

/**
 * Tranx for BscScan对象 tx_tranx_tronscan
 * 
 * @author Jin Liu (angryred@qq.com)
 * @date 2022-03-25
 */
public class TranxTronscan implements Serializable {
	private static final long serialVersionUID = 1L;

	/** TxID */
	private String hash;

	/** 区块高度 */
	private Long block;

	/** 时间戳 */
	private Long timestamp;

	/** FORM */
	private String ownerAddress;

	/** TO */
	private String toAddress;

	/** Value */
	private Long amount;

	/** Gas */
	private Long fee;

	/** 合约地址 */
	private String tokenType;

	/** 主币 */
	private String token;

	/** 代币 */
	private String symbol;

	/** 精度 */
	private Integer decimals;

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getHash() {
		return hash;
	}

	public void setBlock(Long block) {
		this.block = block;
	}

	public Long getBlock() {
		return block;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	public String getOwnerAddress() {
		return ownerAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getAmount() {
		return amount;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public Long getFee() {
		return fee;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setDecimals(Integer decimals) {
		this.decimals = decimals;
	}

	public Integer getDecimals() {
		return decimals;
	}

}
