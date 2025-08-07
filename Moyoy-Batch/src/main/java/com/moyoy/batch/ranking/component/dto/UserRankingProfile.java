package com.moyoy.batch.ranking.component.dto;

import com.moyoy.infra.github.common.GithubProfileResponse;

public record UserRankingProfile(
	String username,
	int followerCount
) {

	public static UserRankingProfile from(GithubProfileResponse githubProfileResponse) {
		return new UserRankingProfile(githubProfileResponse.username(), githubProfileResponse.followers());
	}
}
