package com.moyoy.infra.database.jdbc;

import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OAuthTokenJDBCRepository {

	private final JdbcTemplate jdbc;

	public Optional<String> findAccessTokenValue(String registrationId, String principalName) {
		return jdbc.query(
			"""
			SELECT access_token_value
			FROM oauth2_authorized_client
			WHERE client_registration_id = ? AND principal_name = ?
			""",
			ps -> {
				ps.setString(1, registrationId);
				ps.setString(2, principalName);
			},
			rs -> {
				if (!rs.next()) return Optional.empty();
				byte[] blob = rs.getBytes("access_token_value");
				return Optional.ofNullable(blob == null ? null : new String(blob, java.nio.charset.StandardCharsets.UTF_8));
			}
		);
	}
}
