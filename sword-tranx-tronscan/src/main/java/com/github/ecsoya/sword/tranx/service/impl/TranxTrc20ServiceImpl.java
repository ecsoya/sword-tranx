package com.github.ecsoya.sword.tranx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ecsoya.sword.tranx.domain.TranxTrc20;
import com.github.ecsoya.sword.tranx.mapper.TranxTrc20Mapper;
import com.github.ecsoya.sword.tranx.service.ITranxTrc20Service;

/**
 * Tranx for TronScanService业务层处理
 * 
 * @author Jin Liu (angryred@qq.com)
 * @date 2022-03-25
 */
@Service
public class TranxTrc20ServiceImpl implements ITranxTrc20Service {
	@Autowired
	private TranxTrc20Mapper tranxTrc20Mapper;

	/**
	 * 查询Tranx for TronScan
	 * 
	 * @param hash Tranx for TronScanID
	 * @return Tranx for TronScan
	 */
	@Override
	public TranxTrc20 selectTranxTrc20ById(String hash) {
		return tranxTrc20Mapper.selectTranxTrc20ById(hash);
	}

	/**
	 * 查询Tranx for TronScan列表
	 * 
	 * @param tranxTrc20 Tranx for TronScan
	 * @return Tranx for TronScan
	 */
	@Override
	public List<TranxTrc20> selectTranxTrc20List(TranxTrc20 tranxTrc20) {
		return tranxTrc20Mapper.selectTranxTrc20List(tranxTrc20);
	}

	/**
	 * 新增Tranx for TronScan
	 * 
	 * @param tranxTrc20 Tranx for TronScan
	 * @return 结果
	 */
	@Override
	public int insertTranxTrc20(TranxTrc20 tranxTrc20) {
		return tranxTrc20Mapper.insertTranxTrc20(tranxTrc20);
	}

	/**
	 * 修改Tranx for TronScan
	 * 
	 * @param tranxTrc20 Tranx for TronScan
	 * @return 结果
	 */
	@Override
	public int updateTranxTrc20(TranxTrc20 tranxTrc20) {
		return tranxTrc20Mapper.updateTranxTrc20(tranxTrc20);
	}

	/**
	 * 删除Tranx for TronScan信息
	 * 
	 * @param hash Tranx for TronScanID
	 * @return 结果
	 */
	@Override
	public int deleteTranxTrc20ById(String hash) {
		return tranxTrc20Mapper.deleteTranxTrc20ById(hash);
	}
}
