package com.moyoy.infra.discord.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.moyoy.infra.config.DiscordFeignConfig;
import com.moyoy.infra.discord.dto.DiscordWebhookRequest;

@FeignClient(
	name = "discordWebhookClient",
	url = "${discord.webhook-uri.ranking}",
	configuration = DiscordFeignConfig.class
)
public interface DiscordWebhookClient {

	@PostMapping
	void send(@RequestBody DiscordWebhookRequest body);
}