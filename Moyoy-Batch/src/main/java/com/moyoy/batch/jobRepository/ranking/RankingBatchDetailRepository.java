package com.moyoy.batch.jobRepository.ranking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingBatchDetailRepository extends JpaRepository<RankingBatchDetail, Long> {
	void updateAll(List<RankingBatchDetail> rankingBatchDetails);
}
