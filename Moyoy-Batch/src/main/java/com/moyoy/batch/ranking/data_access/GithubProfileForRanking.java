package com.moyoy.batch.ranking.data_access;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubProfileForRanking(
	@JsonProperty("login") String username,
	@JsonProperty("followers") int followers) {
}
