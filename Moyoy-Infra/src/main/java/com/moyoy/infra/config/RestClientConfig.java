package com.moyoy.infra.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RestClientConfig {

	@Bean
	public RestClient restClient() {
		return RestClient.builder()
			.baseUrl("https://api.github.com")
			.defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
			.defaultHeader(ACCEPT, "application/vnd.github+json")
			.defaultHeader("X-GitHub-Api-Version", "2022-11-28")
			.build();
	}

	@Bean
	public RestClient discordRestClient() {
		return RestClient.builder().build();
	}
}
