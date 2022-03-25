package com.github.ecsoya.sword.tranx.domain;

import com.alibaba.fastjson.JSON;

public class Tronscan {

	private Integer total;

	private Integer rangeTotal;

	private TranxTronscan[] data;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getRangeTotal() {
		return rangeTotal;
	}

	public void setRangeTotal(Integer rangeTotal) {
		this.rangeTotal = rangeTotal;
	}

	public TranxTronscan[] getData() {
		return data;
	}

	public void setData(TranxTronscan[] data) {
		this.data = data;
	}

	public static Tronscan parse(String json) {
		return JSON.parseObject(json, Tronscan.class);
	}
}
