package com.dushan.zhongchou.exception;

public class LoginAcctAlreadyInUseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LoginAcctAlreadyInUseException(String message) {
		super(message);
	}
	
}
