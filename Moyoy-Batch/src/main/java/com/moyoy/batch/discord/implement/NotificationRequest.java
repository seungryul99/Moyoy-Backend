package com.moyoy.batch.discord.implement;

public record NotificationRequest(
	NotificationType type,
	Long rankingBatchHistoryId) {

	public static NotificationRequest of(NotificationType type, Long rankingBatchHistoryId) {
		return new NotificationRequest(type, rankingBatchHistoryId);
	}

}
