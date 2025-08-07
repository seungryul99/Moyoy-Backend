package com.moyoy.batch.ranking.implement;

import org.springframework.stereotype.Component;

import com.moyo.backend.domain.batchLegacy.ranking.data_access.RankingBatchHistoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RankingBatchHistoryReader {

	private final RankingBatchHistoryRepository rankingBatchHistoryRepository;

	public RankingBatchHistory findById(Long rankingBatchHistoryId) {

		return rankingBatchHistoryRepository.findById(rankingBatchHistoryId);
	}
}
