package com.moyoy.batch.ranking.step;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import com.moyoy.batch.ranking.component.processor.RankingCalculator;

@Component
@RequiredArgsConstructor
public class RankingCalculatorStep {

	private final RankingCalculator rankingCalculator;

	public RankingCalculatorResult execute(RankingCalculatorParameters rankingCalculatorParameters) {

		return rankingCalculator.calculate(rankingCalculatorParameters);
	}
}
