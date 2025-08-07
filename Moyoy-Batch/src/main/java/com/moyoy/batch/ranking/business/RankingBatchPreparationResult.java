package com.moyoy.batch.ranking.business;

import com.moyo.backend.domain.batchLegacy.ranking.implement.RankingBatchHistory;
import com.moyo.backend.domain.batchLegacy.ranking.implement.UserRankingBatchSnapshot;

public record RankingBatchPreparationResult(
	UserRankingBatchSnapshot userRankingBatchSnapshot,
	RankingBatchHistory rankingBatchHistory) {
}
