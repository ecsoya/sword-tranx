package com.github.ecsoya.sword.tranx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ecsoya.sword.tranx.domain.TranxTronscan;
import com.github.ecsoya.sword.tranx.mapper.TranxTronscanMapper;
import com.github.ecsoya.sword.tranx.service.ITranxTronscanService;

/**
 * Tranx for BscScanService业务层处理
 * 
 * @author Jin Liu (angryred@qq.com)
 * @date 2022-03-25
 */
@Service
public class TranxTronscanServiceImpl implements ITranxTronscanService {
	@Autowired
	private TranxTronscanMapper tranxTronscanMapper;

	/**
	 * 查询Tranx for BscScan
	 * 
	 * @param hash Tranx for BscScanID
	 * @return Tranx for BscScan
	 */
	@Override
	public TranxTronscan selectTranxTronscanById(String hash) {
		return tranxTronscanMapper.selectTranxTronscanById(hash);
	}

	/**
	 * 查询Tranx for BscScan列表
	 * 
	 * @param tranxTronscan Tranx for BscScan
	 * @return Tranx for BscScan
	 */
	@Override
	public List<TranxTronscan> selectTranxTronscanList(TranxTronscan tranxTronscan) {
		return tranxTronscanMapper.selectTranxTronscanList(tranxTronscan);
	}

	/**
	 * 新增Tranx for BscScan
	 * 
	 * @param tranxTronscan Tranx for BscScan
	 * @return 结果
	 */
	@Override
	public int insertTranxTronscan(TranxTronscan tranxTronscan) {
		return tranxTronscanMapper.insertTranxTronscan(tranxTronscan);
	}

	/**
	 * 修改Tranx for BscScan
	 * 
	 * @param tranxTronscan Tranx for BscScan
	 * @return 结果
	 */
	@Override
	public int updateTranxTronscan(TranxTronscan tranxTronscan) {
		return tranxTronscanMapper.updateTranxTronscan(tranxTronscan);
	}

	/**
	 * 删除Tranx for BscScan信息
	 * 
	 * @param hash Tranx for BscScanID
	 * @return 结果
	 */
	@Override
	public int deleteTranxTronscanById(String hash) {
		return tranxTronscanMapper.deleteTranxTronscanById(hash);
	}
}
