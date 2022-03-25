package com.github.ecsoya.sword.tranx.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 支持账户对象 tx_tranx_symbol
 * 
 * @author Jin Liu (angryred@qq.com)
 * @date 2022-03-25
 */
@Alias("TranxSymbol")
public class TranxSymbol implements Serializable {
	private static final long serialVersionUID = 1L;

	/** symbol */
	private String symbol;

	/** 地址 */
	private String address;

	/** 代币 */
	private String token;

	/** 精度 */
	private Integer decimals;

	/** 区块高度 */
	private Long blockNumber;

	/** 状态 */
	private Integer status;

	private Date createTime;

	private Date updateTime;

	private String remark;

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setDecimals(Integer decimals) {
		this.decimals = decimals;
	}

	public Integer getDecimals() {
		return decimals;
	}

	public void setBlockNumber(Long blockNumber) {
		this.blockNumber = blockNumber;
	}

	public Long getBlockNumber() {
		return blockNumber;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
