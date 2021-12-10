package com.example.exibition.config;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "external")
@PropertySource(value = {"classpath:"})
public class ExternalApiProperties {
	private String host;
	private String port;
	@PostConstruct
	public void log() {
		log.debug("============================================================");
		log.debug("{} {} {}",host,port);
		log.debug("============================================================");
	}
}
