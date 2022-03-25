package com.github.ecsoya.sword.tranx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bscscan")
public class BscscanConfig {
	private String baseUrl = "https://api.bscscan.com/api";

	private String[] apiKeys;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String[] getApiKeys() {
		return apiKeys;
	}

	public void setApiKeys(String[] apiKeys) {
		this.apiKeys = apiKeys;
	}
}
