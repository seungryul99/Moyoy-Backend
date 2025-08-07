package com.moyoy.domain.common.github;

import org.springframework.stereotype.Component;

import com.moyoy.common.exception.github_follow.GithubRateLimitExceedException;
import com.moyoy.infra.github.common.GithubCommonHttpClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GithubApiLimitChecker {

	public static final int GITHUB_MIN_REQUEST_THRESHOLD = 2000;
	private final GithubCommonHttpClient githubCommonHttpClient;

	public void assertCanGithubRequest(String accessToken, Integer githubUserId) {

		int apiLimitRemaining = githubCommonHttpClient.checkApiLimitRemaining(accessToken, githubUserId);

		if (apiLimitRemaining < GITHUB_MIN_REQUEST_THRESHOLD) throw new GithubRateLimitExceedException();
	}

}
