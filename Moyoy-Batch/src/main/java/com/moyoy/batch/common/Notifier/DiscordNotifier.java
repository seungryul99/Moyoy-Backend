package com.moyoy.batch.common.Notifier;

import java.time.Duration;

import org.springframework.stereotype.Component;

import com.moyoy.batch.common.discord.data_access.DiscordRestClientImpl;
import com.moyoy.batch.jobRepository.ranking.RankingBatchHistory;
import com.moyoy.batch.jobRepository.ranking.RankingBatchHistoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DiscordNotifier {

	private final DiscordRestClientImpl discordRestClientImpl;
	private final RankingBatchHistoryRepository rankingBatchHistoryRepository;

	public void sendNotification(NotificationRequest notificationRequest) {
		String message = formatMessage(
			notificationRequest.type(),
			notificationRequest.rankingBatchHistoryId());
		discordRestClientImpl.sendAlarm(message);
	}

	private String formatMessage(NotificationType notificationType, Long rankingBatchHistoryId) {

		return switch (notificationType) {
			case RANKING_BATCH_START ->
				NotificationType.RANKING_BATCH_START.format(rankingBatchHistoryId);

			case RANKING_BATCH_END -> {
				RankingBatchHistory rankingBatchHistory = rankingBatchHistoryRepository.findById(rankingBatchHistoryId).orElseThrow();

				Duration duration = Duration.between(rankingBatchHistory.getStartedAt(), rankingBatchHistory.getFinishedAt());
				long hours = duration.toHours();
				long minutes = duration.toMinutes() % 60;
				long seconds = duration.getSeconds() % 60;

				String durationStr = String.format("%02d시간 %02d분 %02d초", hours, minutes, seconds);

				int totalCount = rankingBatchHistory.getTotalCount();
				int successCount = rankingBatchHistory.getSuccessCount();
				int failCount = rankingBatchHistory.getFailCount();
				double successRate = totalCount == 0 ? 0 : (double)successCount / totalCount * 100;

				yield NotificationType.RANKING_BATCH_END.format(
					rankingBatchHistoryId,
					durationStr,
					totalCount,
					successCount,
					failCount,
					successRate);
			}
		};
	}
}
