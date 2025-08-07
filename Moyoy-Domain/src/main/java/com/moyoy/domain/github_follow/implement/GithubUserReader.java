package com.moyoy.domain.github_follow.implement;

import org.springframework.stereotype.Component;

import com.moyo.backend.domain.github_follow.data_access.GithubFollowHttpClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GithubUserReader {

	private final GithubFollowHttpClient githubFollowHttpClient;

	public GithubFollowUser getGithubUser(Integer githubUserId, String oauthAccessToken) {

		return githubFollowHttpClient.fetchGithubFollowUserById(githubUserId, oauthAccessToken);
	}
}
