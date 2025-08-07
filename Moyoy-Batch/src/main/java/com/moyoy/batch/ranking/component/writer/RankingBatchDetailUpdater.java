package com.moyoy.batch.ranking.component.writer;

import java.util.List;

import org.springframework.stereotype.Component;

import com.moyoy.batch.jobRepository.ranking.RankingBatchDetail;
import com.moyoy.batch.jobRepository.ranking.RankingBatchDetailRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RankingBatchDetailUpdater {

	private final RankingBatchDetailRepository rankingBatchDetailRepository;

	public void updateAll(List<RankingBatchDetail> rankingBatchDetails) {
		rankingBatchDetailRepository.updateAll(rankingBatchDetails);
	}
}
