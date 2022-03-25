package com.github.ecsoya.sword.tranx.config;

import javax.annotation.PostConstruct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = "com.github.ecsoya.sword.tranx")
@MapperScan(basePackages = "com.github.ecsoya.sword.tranx.mapper")
public class TranxConfig {

	@PostConstruct
	public void init() {
	}
}
