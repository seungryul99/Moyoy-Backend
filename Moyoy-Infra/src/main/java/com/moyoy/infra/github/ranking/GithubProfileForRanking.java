package com.moyoy.infra.github.ranking;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubProfileForRanking(
	@JsonProperty("login") String username,
	@JsonProperty("followers") int followers) {
}
