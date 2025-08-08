package com.moyoy.batch.ranking.launcher;

import static java.lang.Thread.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.moyoy.batch.common.Notifier.RankingBatchNotifier;
import com.moyoy.batch.common.Notifier.RankingBatchNotificationRequest;
import com.moyoy.batch.common.Notifier.RankingBatchType;
import com.moyoy.batch.jobRepository.ranking.RankingBatchHistory;
import com.moyoy.batch.jobRepository.ranking.RankingBatchHistoryRepository;
import com.moyoy.batch.ranking.job.RankingBatchJob;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RankingBatchScheduler {

	private final RankingBatchJob rankingBatchJob;
	private final RankingBatchHistoryRepository jobHistoryRepository;
	private final RankingBatchNotifier rankingBatchNotifier;

	@Scheduled(cron = "00 00 00 * * *")
	public void dailyRankingBatch() {

		LocalDateTime now = LocalDateTime.now();
		RankingBatchHistory rankingBatchHistory = RankingBatchHistory.init(now, currentThread().getName());
		jobHistoryRepository.save(rankingBatchHistory);

		log.info("{} 랭킹 배치 작업 시작!", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

		rankingBatchNotifier.sendNotification(
			RankingBatchNotificationRequest.of(RankingBatchType.RANKING_BATCH_START, rankingBatchHistory.getId()));

		rankingBatchJob.execute(rankingBatchHistory);

		rankingBatchNotifier.sendNotification(
			RankingBatchNotificationRequest.of(RankingBatchType.RANKING_BATCH_END, rankingBatchHistory.getId()));
	}
}
