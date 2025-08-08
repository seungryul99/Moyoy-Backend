package com.moyoy.batch.common.Notifier;

import java.time.Duration;

import org.springframework.stereotype.Component;

import com.moyoy.batch.jobRepository.ranking.RankingBatchHistory;
import com.moyoy.batch.jobRepository.ranking.RankingBatchHistoryRepository;
import com.moyoy.infra.discord.dto.DiscordWebhookRequest;
import com.moyoy.infra.discord.feign.DiscordWebhookClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RankingBatchNotifier {

	private final DiscordWebhookClient discordWebhookClient;
	private final RankingBatchHistoryRepository rankingBatchHistoryRepository;

	public void sendNotification(RankingBatchNotificationRequest rankingBatchNotificationRequest) {

		String message = formatMessage(
			rankingBatchNotificationRequest.type(),
			rankingBatchNotificationRequest.batchId()
		);

		discordWebhookClient.send(DiscordWebhookRequest.of(message));
	}

	private String formatMessage(RankingBatchType notificationType, Long batchId) {

		return switch (notificationType) {
			case RANKING_BATCH_START ->
				RankingBatchType.RANKING_BATCH_START.format(batchId);

			case RANKING_BATCH_END -> {
				RankingBatchHistory rankingBatchHistory = rankingBatchHistoryRepository.findById(batchId).orElseThrow();

				Duration duration = Duration.between(rankingBatchHistory.getStartedAt(), rankingBatchHistory.getFinishedAt());
				long hours = duration.toHours();
				long minutes = duration.toMinutes() % 60;
				long seconds = duration.getSeconds() % 60;

				String durationStr = String.format("%02d시간 %02d분 %02d초", hours, minutes, seconds);

				int totalCount = rankingBatchHistory.getTotalCount();
				int successCount = rankingBatchHistory.getSuccessCount();
				int failCount = rankingBatchHistory.getFailCount();
				double successRate = totalCount == 0 ? 0 : (double)successCount / totalCount * 100;

				yield RankingBatchType.RANKING_BATCH_END.format(
					batchId,
					durationStr,
					totalCount,
					successCount,
					failCount,
					successRate);
			}
		};
	}
}
