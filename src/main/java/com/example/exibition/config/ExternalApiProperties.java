package com.example.exibition.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "external")
@PropertySource(value = {"classpath:external.yml"})
public class ExternalApiProperties {
	
	@Value( "${host}" )
	private String host;
	@Value( "${port}" )
	private String port;
	
}
