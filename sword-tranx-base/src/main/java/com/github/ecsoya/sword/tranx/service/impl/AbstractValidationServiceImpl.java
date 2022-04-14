package com.github.ecsoya.sword.tranx.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.github.ecsoya.sword.tranx.domain.TranxBase;
import com.github.ecsoya.sword.tranx.domain.TranxSymbol;
import com.github.ecsoya.sword.tranx.domain.TranxValidation;
import com.github.ecsoya.sword.tranx.service.ITranxScanService;
import com.github.ecsoya.sword.tranx.service.ITranxSymbolService;
import com.github.ecsoya.sword.tranx.service.ITranxValidationService;

public abstract class AbstractValidationServiceImpl implements ITranxValidationService {

	private static final Integer DEFAULT_CONFIRMATIONS = 2;// 2个交易确认

	@Autowired
	private ITranxSymbolService symbolService;

	protected abstract ITranxScanService getTranxScanService();

	protected abstract String getToken();

	@Override
	final public TranxValidation validateTransferIn(String key, String txHash, BigDecimal value, Integer scale) {
		return validateTransferIn(key, txHash, null, value, scale, false);
	}

	@Override
	final public TranxValidation validateTransferInSafely(String key, String txHash, String fromAddress,
			BigDecimal value, Integer scale) {
		return validateTransferIn(key, txHash, fromAddress, value, scale, true);
	}

	@Override
	public TranxValidation validateTransfer(String txHash, String fromAddress, String toAddress, BigDecimal value,
			Integer scale) {
		return validateTransfer(txHash, fromAddress, toAddress, value, scale, !StringUtils.isEmpty(fromAddress),
				!StringUtils.isEmpty(toAddress));
	}

	private TranxValidation validateTransfer(String txHash, String fromAddress, String toAddress, BigDecimal value,
			Integer scale, boolean checkFrom, boolean checkTo) {
		if (value == null || value.doubleValue() <= 0) {
			return TranxValidation.error("验证金额错误");
		}
		ITranxScanService tranxScanService = getTranxScanService();
		if (tranxScanService == null) {
			return TranxValidation.error("内部错误");
		}
		TranxBase tranx = tranxScanService.getTranxByHash(txHash, null);
		if (tranx != null) {
			return validateTransfer(tranx, fromAddress, toAddress, value, scale, checkFrom, checkTo);
		} else {
			List<TranxBase> list = tranxScanService.loadTranxByHash(txHash, DEFAULT_CONFIRMATIONS, fromAddress,
					toAddress);
			if (!list.isEmpty()) {
				for (TranxBase tranxBase : list) {
					TranxValidation result = validateTransfer(tranxBase, fromAddress, toAddress, value, scale,
							checkFrom, checkTo);
					if (result.isOk()) {
						return result;
					}
				}
			}
		}
		return TranxValidation.error("验证金额失败");
	}

	private TranxValidation validateTransfer(TranxBase tranx, String fromAddress, String toAddress, BigDecimal value,
			Integer scale, boolean checkFrom, boolean checkTo) {
		if (tranx == null) {
			return TranxValidation.error("获取交易失败");
		}
		if (checkTo && !equalsIgnoreCase(toAddress, tranx.getToAddress())) {
			return TranxValidation.error("转入地址验证失败");
		}
		if (checkFrom && !equalsIgnoreCase(fromAddress, tranx.getFromAddress())) {
			return TranxValidation.error("转出地址验证失败");
		}
		BigDecimal realValue = tranx.getRealValue();
		if (realValue == null || realValue.doubleValue() <= 0) {
			return TranxValidation.error("验证金额失败");
		}
		if (scale != null && scale >= 0) {
			if (realValue.setScale(scale).doubleValue() == value.setScale(scale).doubleValue()) {
				return TranxValidation.ok();
			}
		} else {
			if (realValue.doubleValue() == value.doubleValue()) {
				return TranxValidation.ok();
			}
		}
		return TranxValidation.error("验证金额失败");
	}

	private TranxValidation validateTransferIn(String key, String txHash, String fromAddress, BigDecimal value,
			Integer scale, boolean safely) {
		if (value == null || value.doubleValue() <= 0) {
			return TranxValidation.error("验证金额错误");
		}
		TranxSymbol token = symbolService.selectTranxSymbolByKey(key);
		if (token == null || !Objects.equals(getToken(), token.getToken())) {
			return TranxValidation.error("验证链错误");
		}
		String toAddress = token.getAddress();
		return validateTransfer(txHash, fromAddress, toAddress, value, scale, safely, true);
	}

	private boolean equalsIgnoreCase(String v1, String v2) {
		if (v1 == null) {
			return v2 == null;
		}
		return v1.equalsIgnoreCase(v2);
	}
}
