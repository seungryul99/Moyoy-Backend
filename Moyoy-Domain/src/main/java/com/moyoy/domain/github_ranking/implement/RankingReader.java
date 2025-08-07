package com.moyoy.domain.github_ranking.implement;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import com.moyo.backend.domain.github_ranking.data_access.RankingRepository;
import com.moyoy.api.github_ranking.implement.RankingPeriod;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RankingReader {

	private final RankingRepository rankingRepository;

	public Ranking getRanking(Long userId) {

		return rankingRepository.findByUserId(userId);
	}

	public RankingSlice getAllRanking(RankingPeriod rankingPeriod, int page, int size) {

		Pageable pageable = PageRequest.of(page, size);

		Slice<Ranking> rankingSlice = rankingRepository.findAll(rankingPeriod, pageable);

		return new RankingSlice(rankingSlice.getContent(), rankingSlice.isLast());
	}

	public RankingSlice getFollowingsRanking(
		List<Integer> followingUserIds,
		RankingPeriod rankingPeriod,
		int page,
		int size) {

		Pageable pageable = PageRequest.of(page, size);
		Slice<Ranking> rankingSlice = rankingRepository.findFollowingUserRankings(followingUserIds, rankingPeriod, pageable);

		return new RankingSlice(rankingSlice.getContent(), rankingSlice.hasNext());
	}
}
