package com.epam.library.security.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;


public class JwtAuthentificationException extends AuthenticationException {

	private HttpStatus httpStatus;

	public JwtAuthentificationException(String msg) {
		super(msg);
	}

	public JwtAuthentificationException(String msg, HttpStatus status) {
		super(msg);
		this.httpStatus = status;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
