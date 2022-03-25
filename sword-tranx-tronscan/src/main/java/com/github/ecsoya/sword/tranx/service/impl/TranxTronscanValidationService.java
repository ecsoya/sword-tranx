package com.github.ecsoya.sword.tranx.service.impl;

import java.math.BigDecimal;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ecsoya.sword.tranx.domain.TranxBase;
import com.github.ecsoya.sword.tranx.domain.TranxSymbol;
import com.github.ecsoya.sword.tranx.domain.TranxValidation;
import com.github.ecsoya.sword.tranx.service.ITranxScanService;
import com.github.ecsoya.sword.tranx.service.ITranxSymbolService;
import com.github.ecsoya.sword.tranx.service.ITranxValidationService;

@Service(ITranxValidationService.TRONSCAN)
public class TranxTronscanValidationService implements ITranxValidationService {

	@Resource(name = ITranxScanService.TRONSCAN)
	private ITranxScanService tranxScanService;

	@Autowired
	private ITranxSymbolService symbolService;

	@Override
	public TranxValidation validateTranx(String symbol, String txHash, BigDecimal value, BigDecimal bounce) {
		if (value == null || value.doubleValue() <= 0) {
			return TranxValidation.error("验证金额错误");
		}
		TranxSymbol token = symbolService.selectTranxSymbolById(symbol);
		if (token == null || !ITranxScanService.TOKEN_TRX.equals(token.getToken())) {
			return TranxValidation.error("验证链错误");
		}
		TranxBase tranx = tranxScanService.getTranxByHash(txHash);
		if (tranx == null) {
			return TranxValidation.error("获取交易失败");
		}
		if (symbol != null && !symbol.equalsIgnoreCase(tranx.getSymbol())) {
			return TranxValidation.error("验证币种失败");
		}
		String address = token.getAddress();
		if (!Objects.equals(address, tranx.getToAddress())) {
			return TranxValidation.error("转账地址验证失败");
		}
		BigDecimal realValue = tranx.getRealValue();
		if (realValue == null || realValue.doubleValue() <= 0) {
			return TranxValidation.error("验证金额失败");
		}
		if (bounce != null) {
			realValue = realValue.subtract(bounce);
		}
		if (realValue.doubleValue() >= value.doubleValue()) {
			return TranxValidation.ok();
		}
		return TranxValidation.error("验证金额失败");
	}

}
