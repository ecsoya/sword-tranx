package com.github.ecsoya.sword.tranx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ecsoya.sword.tranx.domain.TranxBscscan;
import com.github.ecsoya.sword.tranx.mapper.TranxBscscanMapper;
import com.github.ecsoya.sword.tranx.service.ITranxBscscanService;

/**
 * Tranx for BscScanService业务层处理
 * 
 * @author Jin Liu (angryred@qq.com)
 * @date 2022-03-25
 */
@Service
public class TranxBscscanServiceImpl implements ITranxBscscanService {
	@Autowired
	private TranxBscscanMapper tranxBscscanMapper;

	/**
	 * 查询Tranx for BscScan
	 * 
	 * @param hash Tranx for BscScanID
	 * @return Tranx for BscScan
	 */
	@Override
	public TranxBscscan selectTranxBscscanById(String hash) {
		return tranxBscscanMapper.selectTranxBscscanById(hash);
	}

	/**
	 * 查询Tranx for BscScan列表
	 * 
	 * @param tranxBscscan Tranx for BscScan
	 * @return Tranx for BscScan
	 */
	@Override
	public List<TranxBscscan> selectTranxBscscanList(TranxBscscan tranxBscscan) {
		return tranxBscscanMapper.selectTranxBscscanList(tranxBscscan);
	}

	/**
	 * 新增Tranx for BscScan
	 * 
	 * @param tranxBscscan Tranx for BscScan
	 * @return 结果
	 */
	@Override
	public int insertTranxBscscan(TranxBscscan tranxBscscan) {
		return tranxBscscanMapper.insertTranxBscscan(tranxBscscan);
	}

	/**
	 * 修改Tranx for BscScan
	 * 
	 * @param tranxBscscan Tranx for BscScan
	 * @return 结果
	 */
	@Override
	public int updateTranxBscscan(TranxBscscan tranxBscscan) {
		return tranxBscscanMapper.updateTranxBscscan(tranxBscscan);
	}

	/**
	 * 删除Tranx for BscScan信息
	 * 
	 * @param hash Tranx for BscScanID
	 * @return 结果
	 */
	@Override
	public int deleteTranxBscscanById(String hash) {
		return tranxBscscanMapper.deleteTranxBscscanById(hash);
	}
}
