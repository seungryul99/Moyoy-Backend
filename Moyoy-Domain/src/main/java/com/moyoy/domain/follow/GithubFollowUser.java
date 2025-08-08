package com.moyoy.domain.follow;

import com.moyoy.infra.github.dto.GithubFollowUserResponse;

public record GithubFollowUser(
	Integer id,
	String username,
	String profileImgUrl
) implements Comparable<GithubFollowUser> {


	public static GithubFollowUser from(GithubFollowUserResponse githubFollowUserResponse){
		return new GithubFollowUser(
			githubFollowUserResponse.id(),
			githubFollowUserResponse.login(),
			githubFollowUserResponse.avatarUrl()
		);
	}

	// id ASC
	@Override
	public int compareTo(GithubFollowUser otherUser) {
		return this.id.compareTo(otherUser.id);
	}
}
