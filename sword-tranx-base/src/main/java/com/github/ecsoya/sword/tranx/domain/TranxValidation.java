package com.github.ecsoya.sword.tranx.domain;

public class TranxValidation {

	private static final int STATUS_OK = 1;
	private static final int STATUS_ERROR = 0;

	private int status;

	private String message;

	public TranxValidation() {
	}

	public TranxValidation(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static TranxValidation ok() {
		return new TranxValidation(STATUS_OK, null);
	}

	public static TranxValidation error(String message) {
		return new TranxValidation(STATUS_ERROR, message);
	}
}
