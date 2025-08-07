package com.moyoy.batch.ranking.launcher;

import static java.lang.Thread.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.moyoy.batch.common.Notifier.DiscordNotifier;
import com.moyoy.batch.common.Notifier.NotificationRequest;
import com.moyoy.batch.common.Notifier.NotificationType;
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
	private final DiscordNotifier discordNotifier;

	@Scheduled(cron = "00 00 00 * * *")
	public void dailyRankingBatch() {

		LocalDateTime now = LocalDateTime.now();
		RankingBatchHistory rankingBatchHistory = RankingBatchHistory.init(now, currentThread().getName());
		jobHistoryRepository.save(rankingBatchHistory);
		log.info("{} 랭킹 배치 작업 시작!", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

		discordNotifier.sendNotification(NotificationRequest.of(NotificationType.RANKING_BATCH_START, rankingBatchHistory.getId()));

		rankingBatchJob.execute(rankingBatchHistory);

		discordNotifier.sendNotification(NotificationRequest.of(NotificationType.RANKING_BATCH_END, rankingBatchHistory.getId()));
	}
}
