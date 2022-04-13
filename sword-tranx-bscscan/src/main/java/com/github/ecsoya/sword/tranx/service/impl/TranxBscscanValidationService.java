package com.github.ecsoya.sword.tranx.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ecsoya.sword.tranx.domain.TranxBscscan;
import com.github.ecsoya.sword.tranx.domain.TranxSymbol;
import com.github.ecsoya.sword.tranx.domain.TranxValidation;
import com.github.ecsoya.sword.tranx.service.ITranxBscscanService;
import com.github.ecsoya.sword.tranx.service.ITranxScanService;
import com.github.ecsoya.sword.tranx.service.ITranxSymbolService;
import com.github.ecsoya.sword.tranx.service.ITranxValidationService;

@Service(ITranxValidationService.BSCSCAN)
public class TranxBscscanValidationService implements ITranxValidationService {

	@Autowired
	private ITranxBscscanService bscscanService;
	@Autowired
	private ITranxSymbolService symbolService;
	@Override
	public TranxValidation validateTranx(String key, String txHash, BigDecimal value, BigDecimal bounce) {
		if (value == null || value.doubleValue() <= 0) {
			return TranxValidation.error("验证金额错误");
		}
		TranxSymbol token = symbolService.selectTranxSymbolByKey(key);
		if (token == null || !ITranxScanService.TOKEN_BNB.equals(token.getToken())) {
			return TranxValidation.error("验证链错误");
		}
		TranxBscscan tranx = bscscanService.selectTranxBscscanById(txHash);
		if (tranx == null || key == null || !key.equalsIgnoreCase(tranx.getSymbol())) {
			return TranxValidation.error("获取交易失败");
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
