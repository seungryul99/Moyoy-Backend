package com.moyoy.batch.ranking.data_access;

import org.springframework.stereotype.Repository;

import com.moyo.backend.domain.batchLegacy.ranking.implement.RankingBatchHistory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RankingBatchHistoryRepositoryImpl implements RankingBatchHistoryRepository {

	private final RankingBatchHistoryJpaRepository rankingBatchHistoryJpaRepository;

	@Override
	public void save(RankingBatchHistory rankingBatchHistory) {
		rankingBatchHistoryJpaRepository.save(rankingBatchHistory);
	}

	@Override
	public RankingBatchHistory findById(Long rankingBatchHistoryId) {
		return rankingBatchHistoryJpaRepository.findById(rankingBatchHistoryId).orElse(null);
	}
}
