package com.github.ecsoya.sword.tranx.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Tranx for BscScan对象 tx_tranx_bscscan
 * 
 * @author Jin Liu (angryred@qq.com)
 * @date 2022-03-25
 */
@Alias("TranxBscscan")
public class TranxBscscan implements Serializable {
	private static final long serialVersionUID = 1L;

	/** TxID */
	private String hash;

	/** 区块高度 */
	private Long blockNumber;

	/** 时间戳 */
	private Long timeStamp;

	/** FORM */
	@JSONField(name = "from")
	private String fromAddress;

	/** TO */
	@JSONField(name = "to")
	private String toAddress;

	/** Value */
	private Long value;

	/** Gas */
	private Long gas;

	/** Gas Price */
	private Long gasPrice;

	/** isError */
	private Integer isError;

	/** Status */
	private Integer txreceiptStatus;

	/** 合约地址 */
	private String contractAddress;

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

	public void setBlockNumber(Long blockNumber) {
		this.blockNumber = blockNumber;
	}

	public Long getBlockNumber() {
		return blockNumber;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return value;
	}

	public void setGas(Long gas) {
		this.gas = gas;
	}

	public Long getGas() {
		return gas;
	}

	public void setGasPrice(Long gasPrice) {
		this.gasPrice = gasPrice;
	}

	public Long getGasPrice() {
		return gasPrice;
	}

	public void setIsError(Integer isError) {
		this.isError = isError;
	}

	public Integer getIsError() {
		return isError;
	}

	public void setTxreceiptStatus(Integer txreceiptStatus) {
		this.txreceiptStatus = txreceiptStatus;
	}

	public Integer getTxreceiptStatus() {
		return txreceiptStatus;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public String getContractAddress() {
		return contractAddress;
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

	public BigDecimal getRealValue() {
		if (value == null) {
			return null;
		}
		BigDecimal pow = BigDecimal.ONE;
		if (decimals != null) {
			pow = BigDecimal.valueOf(10).pow(decimals.intValue());
		}
		return BigDecimal.valueOf(value).divide(pow, 6, RoundingMode.HALF_UP);
	}
}
