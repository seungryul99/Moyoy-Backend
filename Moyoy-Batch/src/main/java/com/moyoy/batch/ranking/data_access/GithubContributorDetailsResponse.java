package com.moyoy.batch.ranking.data_access;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubContributorDetailsResponse(
	@JsonProperty("login") String username) {
}
