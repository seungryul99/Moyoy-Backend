package com.moyoy.infra.github.ranking;

import java.util.List;


public interface GithubRankingHttpClient {

	ResponseEntity<GithubProfileForRanking> fetchRankingPreflight(Integer githubUserId, String accessToken);

	List<GithubRepoDetailsResponse> fetchPagedRepos(int currentPage, String accessToken);

	List<GithubContributorDetailsResponse> fetchPagedContributors(int currentPage, String repoFullName, String accessToken);

	ResponseEntity<String> fetchContributorCommitActivity(String repoFullName, String accessToken);
}
