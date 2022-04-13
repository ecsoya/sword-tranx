package com.github.ecsoya.sword.tranx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ecsoya.sword.tranx.domain.TranxSymbol;
import com.github.ecsoya.sword.tranx.mapper.TranxSymbolMapper;
import com.github.ecsoya.sword.tranx.service.ITranxSymbolService;

/**
 * 支持账户Service业务层处理
 * 
 * @author Jin Liu (angryred@qq.com)
 * @date 2022-03-25
 */
@Service
public class TranxSymbolServiceImpl implements ITranxSymbolService {
	@Autowired
	private TranxSymbolMapper tranxSymbolMapper;

	/**
	 * 查询支持账户
	 * 
	 * @param symbol 支持账户ID
	 * @return 支持账户
	 */
	@Override
	public TranxSymbol selectTranxSymbolById(Long id) {
		return tranxSymbolMapper.selectTranxSymbolById(id);
	}
	
	@Override
	public TranxSymbol selectTranxSymbolByKey(String key) {
		return tranxSymbolMapper.selectTranxSymbolByKey(key);
	}

	/**
	 * 查询支持账户列表
	 * 
	 * @param tranxSymbol 支持账户
	 * @return 支持账户
	 */
	@Override
	public List<TranxSymbol> selectTranxSymbolList(TranxSymbol tranxSymbol) {
		return tranxSymbolMapper.selectTranxSymbolList(tranxSymbol);
	}

	/**
	 * 新增支持账户
	 * 
	 * @param tranxSymbol 支持账户
	 * @return 结果
	 */
	@Override
	public int insertTranxSymbol(TranxSymbol tranxSymbol) {
		return tranxSymbolMapper.insertTranxSymbol(tranxSymbol);
	}

	/**
	 * 修改支持账户
	 * 
	 * @param tranxSymbol 支持账户
	 * @return 结果
	 */
	@Override
	public int updateTranxSymbol(TranxSymbol tranxSymbol) {
		return tranxSymbolMapper.updateTranxSymbol(tranxSymbol);
	}

	/**
	 * 删除支持账户信息
	 * 
	 * @param symbol 支持账户ID
	 * @return 结果
	 */
	@Override
	public int deleteTranxSymbolById(Long id) {
		return tranxSymbolMapper.deleteTranxSymbolById(id);
	}
}
