package com.github.ecsoya.sword.tranx.service.impl;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.ecsoya.sword.tranx.domain.TranxBase;
import com.github.ecsoya.sword.tranx.domain.TranxSymbol;
import com.github.ecsoya.sword.tranx.domain.TranxValidation;
import com.github.ecsoya.sword.tranx.service.ITranxScanService;
import com.github.ecsoya.sword.tranx.service.ITranxSymbolService;
import com.github.ecsoya.sword.tranx.service.ITranxValidationService;

public abstract class AbstractValidationServiceImpl implements ITranxValidationService {
	@Autowired
	private ITranxSymbolService symbolService;

	protected abstract TranxBase getTranxByHash(String txHash, String key);

	@Override
	final public TranxValidation validateTransferIn(String key, String txHash, BigDecimal value, BigDecimal bounce) {
		return validateTransferIn(key, txHash, null, value, bounce, false);
	}

	@Override
	final public TranxValidation validateTransferInSafely(String key, String txHash, String fromAddress,
			BigDecimal value, BigDecimal bounce) {
		return validateTransferIn(key, txHash, fromAddress, value, bounce, true);
	}

	private TranxValidation validateTransferIn(String key, String txHash, String fromAddress, BigDecimal value,
			BigDecimal bounce, boolean safely) {
		if (value == null || value.doubleValue() <= 0) {
			return TranxValidation.error("验证金额错误");
		}
		TranxSymbol token = symbolService.selectTranxSymbolByKey(key);
		if (token == null || !ITranxScanService.TOKEN_TRX.equals(token.getToken())) {
			return TranxValidation.error("验证链错误");
		}
		TranxBase tranx = getTranxByHash(txHash, null);
		if (tranx == null) {
			return TranxValidation.error("获取交易失败");
		}
		String address = token.getAddress();
		if (!Objects.equals(address, tranx.getToAddress())) {
			return TranxValidation.error("转入地址验证失败");
		}
		if (safely && !Objects.equals(fromAddress, tranx.getFromAddress())) {
			return TranxValidation.error("转出地址验证失败");
		}
		BigDecimal realValue = tranx.getRealValue();
		if (realValue == null || realValue.doubleValue() <= 0) {
			return TranxValidation.error("验证金额失败");
		}
		if (bounce != null) {
			realValue = realValue.subtract(bounce);
		}
		if (realValue.doubleValue() == value.doubleValue()) {
			return TranxValidation.ok();
		}
		return TranxValidation.error("验证金额失败");
	}
}
