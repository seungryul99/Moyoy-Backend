package com.moyoy.batch.ranking.component.dto;

import com.moyoy.infra.github.ranking.GithubRepoDetailsResponse;

public record GithubRepoDetails(
	String repoName,
	String ownerName,
	int starCount) {

	public static GithubRepoDetails from(GithubRepoDetailsResponse response) {
		return new GithubRepoDetails(response.repoFullName(), response.owner().name(), response.startCount());
	}
}
