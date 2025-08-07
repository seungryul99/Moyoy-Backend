package com.moyoy.domain.github_ranking.data_access;

import static com.moyo.backend.domain.github_ranking.implement.QRanking.*;
import static com.moyo.backend.domain.user.implement.QUser.*;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import com.moyo.backend.domain.github_ranking.implement.Ranking;
import com.moyo.backend.domain.github_ranking.implement.RankingPeriod;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RankingQueryDslRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public Slice<Ranking> findAll(RankingPeriod duration, Pageable pageable) {

		OrderSpecifier<?> orderCondition = switch (duration) {
			case WEEK -> ranking.weeklyPoint.desc();
			case MONTH -> ranking.monthlyPoint.desc();
			case YEAR -> ranking.yearlyPoint.desc();
		};

		List<Ranking> rankings = jpaQueryFactory
			.selectFrom(ranking)
			.orderBy(orderCondition)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize() + 1)
			.fetch();

		boolean hasNext = rankings.size() > pageable.getPageSize();
		if (hasNext)
			rankings.removeLast();

		return new SliceImpl<>(rankings, pageable, hasNext);
	}

	public Slice<Ranking> findByUserIds(List<Integer> followingUserIds, RankingPeriod rankingPeriod, Pageable pageable) {

		OrderSpecifier<?> orderCondition = switch (rankingPeriod) {
			case WEEK -> ranking.weeklyPoint.desc();
			case MONTH -> ranking.monthlyPoint.desc();
			case YEAR -> ranking.yearlyPoint.desc();
		};

		List<Ranking> rankings = jpaQueryFactory
			.selectFrom(ranking)
			.where(ranking.userId.in(
				JPAExpressions
					.select(user.id)
					.from(user)
					.where(user.githubUserId.in(followingUserIds))))
			.orderBy(orderCondition)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize() + 1)
			.fetch();

		boolean hasNext = rankings.size() > pageable.getPageSize();
		if (hasNext)
			rankings.removeLast();

		return new SliceImpl<>(rankings, pageable, hasNext);
	}
}
