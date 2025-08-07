package com.moyoy.common.exception.github_follow;

import static com.moyo.backend.common.exception.github_follow.FollowErrorCode.LIMIT_EXCEED;

import com.moyoy.common.exception.MoyoException;

public class GithubRateLimitExceedException extends MoyoException {
	public GithubRateLimitExceedException() {
		super(LIMIT_EXCEED);
	}
}
