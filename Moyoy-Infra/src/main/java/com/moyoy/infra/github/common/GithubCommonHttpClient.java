package com.moyoy.infra.github.common;

public interface GithubCommonHttpClient {

	GithubProfileResponse fetchUserProfile(String accessToken, Integer githubUserId);

	int checkApiLimitRemaining(String accessToken, Integer githubUserId);
}
