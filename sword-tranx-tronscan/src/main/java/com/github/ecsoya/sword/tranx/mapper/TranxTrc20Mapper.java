package com.github.ecsoya.sword.tranx.mapper;

import java.util.List;

import com.github.ecsoya.sword.tranx.domain.TranxTrc20;

/**
 * Tranx for TronScanMapper接口
 * 
 * @author Jin Liu (angryred@qq.com)
 * @date 2022-03-25
 */
public interface TranxTrc20Mapper {
	/**
	 * 查询Tranx for TronScan
	 * 
	 * @param hash Tranx for TronScanID
	 * @return Tranx for TronScan
	 */
	public TranxTrc20 selectTranxTrc20ById(String hash);

	/**
	 * 查询Tranx for TronScan列表
	 * 
	 * @param tranxTrc20 Tranx for TronScan
	 * @return Tranx for TronScan集合
	 */
	public List<TranxTrc20> selectTranxTrc20List(TranxTrc20 tranxTrc20);

	/**
	 * 新增Tranx for TronScan
	 * 
	 * @param tranxTrc20 Tranx for TronScan
	 * @return 结果
	 */
	public int insertTranxTrc20(TranxTrc20 tranxTrc20);

	/**
	 * 修改Tranx for TronScan
	 * 
	 * @param tranxTrc20 Tranx for TronScan
	 * @return 结果
	 */
	public int updateTranxTrc20(TranxTrc20 tranxTrc20);

	/**
	 * 删除Tranx for TronScan
	 * 
	 * @param hash Tranx for TronScanID
	 * @return 结果
	 */
	public int deleteTranxTrc20ById(String hash);

	/**
	 * 批量删除Tranx for TronScan
	 * 
	 * @param hashs 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTranxTrc20ByIds(String[] hashs);
}
