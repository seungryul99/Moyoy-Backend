package com.moyoy.batch.ranking.component.dto;

import com.moyoy.infra.github.ranking.GithubContributorDetailsResponse;

public record GithubContributorDetails(
	String username) {
	public static GithubContributorDetails from(GithubContributorDetailsResponse githubContributorDetailsResponse) {
		return new GithubContributorDetails(githubContributorDetailsResponse.username());
	}
}
