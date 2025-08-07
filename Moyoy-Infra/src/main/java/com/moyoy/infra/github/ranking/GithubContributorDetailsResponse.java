package com.moyoy.infra.github.ranking;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubContributorDetailsResponse(
	@JsonProperty("login") String username) {
}
