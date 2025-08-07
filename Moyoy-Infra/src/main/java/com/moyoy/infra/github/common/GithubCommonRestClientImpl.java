package com.moyoy.infra.github.common;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GithubCommonRestClientImpl implements GithubCommonHttpClient{

	private static final String GITHUB_API_RATE_LIMIT_REMAINING_HEADER = "X-RateLimit-Remaining";

	private final RestClient restClient;

	@Override
	public GithubProfileResponse fetchUserProfile(String accessToken, Integer githubUserId) {

		return restClient.get()
			.uri("/user/{userId}", githubUserId)
			.headers(header -> header.setBearerAuth(accessToken))
			.retrieve()
			.toEntity(GithubProfileResponse.class).getBody();
	}

	@Override
	public int checkApiLimitRemaining(String accessToken, Integer githubUserId) {

		ResponseEntity<?> response = restClient.get()
			.uri("/user/{userId}", githubUserId)
			.headers(header -> header.setBearerAuth(accessToken))
			.retrieve()
			.toEntity(GithubProfileResponse.class);

		return Integer.parseInt(response.getHeaders().get(GITHUB_API_RATE_LIMIT_REMAINING_HEADER).getFirst());
	}

}
