package com.moyoy.domain.github_ranking.implement;

import java.util.List;

import com.moyoy.api.github_ranking.implement.Ranking;

public record RankingSlice(
	List<Ranking> rankingList,
	boolean isLast) {

}
