package com.moyoy.domain.follow.implement;

import org.springframework.stereotype.Component;

import com.moyoy.infra.github.follow.GithubFollowHttpClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GithubUserReader {

	private final GithubFollowHttpClient githubFollowHttpClient;

	public GithubFollowUser getGithubUser(Integer githubUserId, String oauthAccessToken) {

		return githubFollowHttpClient.fetchGithubFollowUserById(githubUserId, oauthAccessToken);
	}
}
