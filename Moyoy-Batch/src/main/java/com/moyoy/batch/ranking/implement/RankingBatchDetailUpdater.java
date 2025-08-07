package com.moyoy.batch.ranking.implement;

import java.util.List;

import org.springframework.stereotype.Component;

import com.moyo.backend.domain.batchLegacy.ranking.data_access.RankingBatchDetailRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RankingBatchDetailUpdater {

	private final RankingBatchDetailRepository rankingBatchDetailRepository;

	public void updateAll(List<RankingBatchDetail> rankingBatchDetails) {
		rankingBatchDetailRepository.updateAll(rankingBatchDetails);
	}
}
