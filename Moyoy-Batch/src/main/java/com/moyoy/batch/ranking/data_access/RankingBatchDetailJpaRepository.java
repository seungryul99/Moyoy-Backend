package com.moyoy.batch.ranking.data_access;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moyo.backend.domain.batchLegacy.ranking.implement.RankingBatchDetail;

public interface RankingBatchDetailJpaRepository extends JpaRepository<RankingBatchDetail, Long> {}
