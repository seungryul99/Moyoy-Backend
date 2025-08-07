package com.moyoy.domain.github_ranking.implement;

import com.moyo.backend.domain.user.implement.User;
import com.moyoy.api.github_ranking.implement.Ranking;

public record RankingWithUser(
	Ranking ranking,
	User user) {

}
