package com.github.ecsoya.sword.tranx.domain;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Tranx for TronScan对象 tx_tranx_trc20
 * 
 * @author Jin Liu (angryred@qq.com)
 * @date 2022-03-25
 */
public class TranxTrc20 extends TranxBase implements Serializable {
	private static final long serialVersionUID = 1L;

	/** Level */
	private String level;

	/** 名称 */
	private String name;

	/** Value */
	@JSONField(alternateNames = "amount_str")
	private String amountStr;

	/** Gas */
	private String type;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmountStr() {
		return amountStr;
	}

	public void setAmountStr(String amountStr) {
		this.amountStr = amountStr;
		if (amountStr != null) {
			try {
				setAmount(amountStr.trim());
			} catch (NumberFormatException e) {
			}
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
