package com.moyoy.infra.github.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubProfileResponse(
	@JsonProperty("login") String username,
	@JsonProperty("followers") int followers
) {
}
