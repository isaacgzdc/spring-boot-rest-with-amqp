package com.example.exibition.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ConfigurationProperties(prefix = "external")
@PropertySource(value = {"classpath:external.yml"})
public class ExternalApiProperties {
	
	@Value( "${host}" )
	private String host;
	@Value( "${port}" )
	private String port;
	
	@PostConstruct
	public void log() {
		log.debug("============================================================");
		log.debug("{} {} {}",host,port);
		log.debug("============================================================");
	}
}
