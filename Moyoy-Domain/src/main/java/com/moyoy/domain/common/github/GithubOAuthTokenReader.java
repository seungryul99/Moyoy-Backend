package com.moyoy.domain.common.github;

import static com.moyoy.common.constant.MoyoConstants.*;

import org.springframework.stereotype.Component;

import com.moyoy.infra.database.jdbc.OAuthTokenJDBCRepository;

import lombok.RequiredArgsConstructor;

/**
 *  API, Batch 등의 서비스에서 같이 써야 하는데
 *  Batch 모듈은 Spring Security를 사용하지 않고
 *  org.springframework.boot:spring-boot-starter-oauth2-client 를 사용할 수 없음.
 *
 *  따라서 API 측에서 사용자와 상호작용하면서 OAuth2AuthorizedClientService 를 사용해 DB에
 *  유저 OAuth 토큰 관련 정보들을 관리하고
 *
 *  이를 읽어가는 GithubOAuthTokenReader는 OAuth2AuthorizedClientService 없이
 *  JDBC로 구현한 Repository를 사용함.
 */

@Component
@RequiredArgsConstructor
public class GithubOAuthTokenReader {

	private final OAuthTokenJDBCRepository oAuthTokenJDBCRepository;

	public String getGithubAccessToken(Long userId) {

		return oAuthTokenJDBCRepository.findAccessTokenValue(GITHUB_REGISTRATION_ID, userId.toString()).orElseThrow();
	}
}
