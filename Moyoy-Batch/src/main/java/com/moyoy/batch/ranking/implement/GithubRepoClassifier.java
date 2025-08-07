package com.moyoy.batch.ranking.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GithubRepoClassifier {

	private final GithubContributorChecker githubContributorChecker;

	public List<GithubRepoDetails> classify(
		List<GithubRepoDetails> allRepos,
		String currentUsername,
		String accessToken) {

		List<GithubRepoDetails> userOwnedRepos = allRepos.stream()
			.filter(repo -> repo.ownerName().equals(currentUsername))
			.toList();

		List<GithubRepoDetails> userContributedRepos = allRepos.stream()
			.filter(repo -> !repo.ownerName().equals(currentUsername))
			.filter(repo -> githubContributorChecker.isContributor(repo.repoName(), currentUsername, accessToken))
			.toList();

		List<GithubRepoDetails> finalRankingCandidateRepos = new ArrayList<>();
		finalRankingCandidateRepos.addAll(userOwnedRepos);
		finalRankingCandidateRepos.addAll(userContributedRepos);

		return finalRankingCandidateRepos;
	}
}
