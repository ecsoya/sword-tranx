package com.github.ecsoya.sword.tranx.domain;

import java.util.Objects;

public class TranxTrx {

	private String hash;

	private TranxTrc20[] trc20TransferInfo;

	/** 确认次数 */
	private Integer confirmations;
	
	private Boolean confirmed;
	
	private String contractRet;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public TranxTrc20[] getTrc20TransferInfo() {
		return trc20TransferInfo;
	}

	public void setTrc20TransferInfo(TranxTrc20[] trc20TransferInfo) {
		this.trc20TransferInfo = trc20TransferInfo;
	}

	public Integer getConfirmations() {
		return confirmations;
	}

	public void setConfirmations(Integer confirmations) {
		this.confirmations = confirmations;
	}

	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public String getContractRet() {
		return contractRet;
	}

	public void setContractRet(String contractRet) {
		this.contractRet = contractRet;
	}
	
	public boolean isSuccess() {
		return confirmed != null && Boolean.TRUE.equals(confirmed) && Objects.equals("SUCCESS", contractRet);
	}
}
