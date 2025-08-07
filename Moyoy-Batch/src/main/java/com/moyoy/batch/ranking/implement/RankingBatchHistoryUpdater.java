package com.moyoy.batch.ranking.implement;

import java.util.List;

import org.springframework.stereotype.Component;

import com.moyo.backend.domain.batchLegacy.ranking.data_access.RankingBatchHistoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RankingBatchHistoryUpdater {

	private final RankingBatchHistoryRepository rankingBatchHistoryRepository;

	public void updateFinalResult(RankingBatchHistory rankingBatchHistory, List<RankingBatchResult> rankingBatchResults) {

		int totalSuccess = rankingBatchResults.stream()
			.mapToInt(RankingBatchResult::successCount)
			.sum();

		int totalFail = rankingBatchResults.stream()
			.mapToInt(RankingBatchResult::failCount)
			.sum();

		rankingBatchHistory.finalizeBatch(totalSuccess, totalFail);

		rankingBatchHistoryRepository.save(rankingBatchHistory);
	}

	public void update(RankingBatchHistory rankingBatchHistory) {

		rankingBatchHistoryRepository.save(rankingBatchHistory);
	}
}
