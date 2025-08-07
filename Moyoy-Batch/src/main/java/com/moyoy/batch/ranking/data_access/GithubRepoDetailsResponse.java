package com.moyoy.batch.ranking.data_access;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubRepoDetailsResponse(
	@JsonProperty("full_name") String repoFullName,
	@JsonProperty("stargazers_count") int startCount,
	@JsonProperty("owner") Owner owner) {

	public record Owner(@JsonProperty("login") String name) {
	}
}
