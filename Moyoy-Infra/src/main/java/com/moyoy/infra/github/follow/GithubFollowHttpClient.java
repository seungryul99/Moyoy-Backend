package com.moyoy.infra.github.follow;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface GithubFollowHttpClient {
	ResponseEntity<GithubUserFollowStats> fetchFollowStats(Integer githubUserId, String accessToken);

	List<GithubFollowUser> fetchPagedFollowers(int currentPage, String accessToken);

	List<GithubFollowUser> fetchPagedFollowings(int currentPage, String accessToken);

	GithubFollowUser fetchGithubFollowUserById(Integer githubUserId, String accessToken);

	int follow(String targetUsername, String accessToken);

	int unfollow(String targetUsername, String accessToken);
}
