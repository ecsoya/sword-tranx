package com.github.ecsoya.sword.tranx.service;

import java.util.List;

import com.github.ecsoya.sword.tranx.domain.TranxTronscan;

/**
 * Tranx for BscScanService接口
 * 
 * @author Jin Liu (angryred@qq.com)
 * @date 2022-03-25
 */
public interface ITranxTronscanService {
	/**
	 * 查询Tranx for BscScan
	 * 
	 * @param hash Tranx for BscScanID
	 * @return Tranx for BscScan
	 */
	public TranxTronscan selectTranxTronscanById(String hash);

	/**
	 * 查询Tranx for BscScan列表
	 * 
	 * @param tranxTronscan Tranx for BscScan
	 * @return Tranx for BscScan集合
	 */
	public List<TranxTronscan> selectTranxTronscanList(TranxTronscan tranxTronscan);

	/**
	 * 新增Tranx for BscScan
	 * 
	 * @param tranxTronscan Tranx for BscScan
	 * @return 结果
	 */
	public int insertTranxTronscan(TranxTronscan tranxTronscan);

	/**
	 * 修改Tranx for BscScan
	 * 
	 * @param tranxTronscan Tranx for BscScan
	 * @return 结果
	 */
	public int updateTranxTronscan(TranxTronscan tranxTronscan);

	/**
	 * 删除Tranx for BscScan信息
	 * 
	 * @param hash Tranx for BscScanID
	 * @return 结果
	 */
	public int deleteTranxTronscanById(String hash);
}
