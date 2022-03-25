package com.github.ecsoya.sword.tranx.domain;

public class TranxTrx {

	private String hash;

	private TranxTrc20[] trc20TransferInfo;

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
}
