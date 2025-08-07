package com.moyoy.api.auth.oauth.component.exception;

import static com.moyo.backend.common.constant.MoyoConstants.*;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.moyo.backend.common.exception.auth.AuthErrorCode;
import com.moyo.backend.common.implement.ErrorResponseWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *  인증되지 않은 사용자가 인증이 필요한 자원에 접근할 때 인증 실패 에러 처리
 *
 *  인증된 사용자지만 권한이 부족하면 AccessDeniedHandler 에서 처리
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final ErrorResponseWriter errorResponseWriter;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

		log.warn("Authentication Entry Point 예외 처리, 인증되지 않은 사용자가 인증이 필요한 자원에 접근을 시도했습니다. : {}", authException.getMessage());
		errorResponseWriter.writeErrorResponse(response, UNAUTHORIZED, AuthErrorCode.UNAUTHORIZED_USER);
	}
}
