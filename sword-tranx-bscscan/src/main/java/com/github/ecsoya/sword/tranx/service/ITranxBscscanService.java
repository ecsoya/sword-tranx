package com.github.ecsoya.sword.tranx.service;

import java.util.List;

import com.github.ecsoya.sword.tranx.domain.TranxBscscan;

/**
 * Tranx for BscScanService接口
 * 
 * @author Jin Liu (angryred@qq.com)
 * @date 2022-03-25
 */
public interface ITranxBscscanService {
	/**
	 * 查询Tranx for BscScan
	 * 
	 * @param hash Tranx for BscScanID
	 * @return Tranx for BscScan
	 */
	public TranxBscscan selectTranxBscscanById(String hash);

	/**
	 * 查询Tranx for BscScan列表
	 * 
	 * @param tranxBscscan Tranx for BscScan
	 * @return Tranx for BscScan集合
	 */
	public List<TranxBscscan> selectTranxBscscanList(TranxBscscan tranxBscscan);

	/**
	 * 新增Tranx for BscScan
	 * 
	 * @param tranxBscscan Tranx for BscScan
	 * @return 结果
	 */
	public int insertTranxBscscan(TranxBscscan tranxBscscan);

	/**
	 * 修改Tranx for BscScan
	 * 
	 * @param tranxBscscan Tranx for BscScan
	 * @return 结果
	 */
	public int updateTranxBscscan(TranxBscscan tranxBscscan);

	/**
	 * 删除Tranx for BscScan信息
	 * 
	 * @param hash Tranx for BscScanID
	 * @return 结果
	 */
	public int deleteTranxBscscanById(String hash);
}
