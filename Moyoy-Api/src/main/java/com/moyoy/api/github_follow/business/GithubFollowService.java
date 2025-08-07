package com.moyoy.api.github_follow.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moyo.backend.domain.github_follow.implement.GithubFollowRelation;
import com.moyo.backend.domain.github_follow.implement.GithubFollowRelationReader;
import com.moyo.backend.domain.github_follow.implement.GithubFollowUpdater;
import com.moyo.backend.domain.github_follow.implement.GithubFollowUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GithubFollowService {

	private final GithubFollowRelationReader githubFollowRelationReader;
	private final GithubFollowUpdater githubFollowUpdater;

	public GithubFollowDetectionResult getFollowUserSlice(Long userId, GithubFollowDetection followDetection) {

		// TODO: forceSync 검증 로직 추가
		GithubFollowRelation githubFollowRelation = githubFollowRelationReader.findByUserId(userId, followDetection.forceSync());
		List<GithubFollowUser> filteredFollowUsers = githubFollowRelation.filterUsersByDetectType(followDetection.detectType());

		return GithubFollowDetectionResult.from(filteredFollowUsers, followDetection, githubFollowRelation.getCreatedAt());
	}

	public void follow(Long currentUserId, Integer targetGithubUserId) {

		githubFollowUpdater.follow(currentUserId, targetGithubUserId);
	}

	public void unfollow(Long currentUserId, Integer targetGithubUserId) {

		githubFollowUpdater.unfollow(currentUserId, targetGithubUserId);
	}
}
