package com.github.ecsoya.sword.tranx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "tronscan")
public class TronscanConfig {
	private String baseUrl = "https://apilist.tronscan.org/api/";

	private String path = "transaction";

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
