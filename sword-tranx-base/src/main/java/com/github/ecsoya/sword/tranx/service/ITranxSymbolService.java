package com.github.ecsoya.sword.tranx.service;

import java.util.List;

import com.github.ecsoya.sword.tranx.domain.TranxSymbol;

/**
 * 支持账户Service接口
 * 
 * @author Jin Liu (angryred@qq.com)
 * @date 2022-03-25
 */
public interface ITranxSymbolService {
	/**
	 * 查询支持账户
	 * 
	 * @param symbol 支持账户ID
	 * @return 支持账户
	 */
	public TranxSymbol selectTranxSymbolById(Long id);
	public TranxSymbol selectTranxSymbolByKey(String key);

	/**
	 * 查询支持账户列表
	 * 
	 * @param tranxSymbol 支持账户
	 * @return 支持账户集合
	 */
	public List<TranxSymbol> selectTranxSymbolList(TranxSymbol tranxSymbol);

	/**
	 * 新增支持账户
	 * 
	 * @param tranxSymbol 支持账户
	 * @return 结果
	 */
	public int insertTranxSymbol(TranxSymbol tranxSymbol);

	/**
	 * 修改支持账户
	 * 
	 * @param tranxSymbol 支持账户
	 * @return 结果
	 */
	public int updateTranxSymbol(TranxSymbol tranxSymbol);

	/**
	 * 删除支持账户信息
	 * 
	 * @param symbol 支持账户ID
	 * @return 结果
	 */
	public int deleteTranxSymbolById(Long id);
}
