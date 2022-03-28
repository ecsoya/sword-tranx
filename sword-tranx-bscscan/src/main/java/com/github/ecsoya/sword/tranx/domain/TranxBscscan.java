package com.github.ecsoya.sword.tranx.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * Tranx for BscScan对象 tx_tranx_bscscan
 * 
 * @author Jin Liu (angryred@qq.com)
 * @date 2022-03-25
 */
@Alias("TranxBscscan")
public class TranxBscscan extends TranxBase implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 区块高度 */
	private Long blockNumber;

	/** 时间戳 */
	private Long timeStamp;

	/** Gas */
	private Long gas;

	/** Gas Price */
	private Long gasPrice;

	/** isError */
	private Integer isError;

	/** Status */
	private Integer txreceiptStatus;
	
	private String token;

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public Integer getDecimals() {
		Integer value = super.getDecimals();
		if (value == null) {
			value = 18;
		}
		return value;
	}
}
