package com.github.ecsoya.sword.tranx.domain;

import com.alibaba.fastjson.JSON;

public class Bscscan {

	public static final String STATUS_OK = "1";
	public static final String STATUS_ERROR = "0";

	private String status;

	private String message;

	private TranxBscscan[] result;

	public Bscscan() {
	}

	public Bscscan(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TranxBscscan[] getResult() {
		return result;
	}

	public void setResult(TranxBscscan[] result) {
		this.result = result;
	}

	public boolean isOk() {
		return STATUS_OK.equals(status);
	}

	public static Bscscan error(String message) {
		return new Bscscan(STATUS_ERROR, message);
	}

	public static Bscscan parse(String json) {
		return JSON.parseObject(json, Bscscan.class);
	}

}
