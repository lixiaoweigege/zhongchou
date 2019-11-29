package com.dushan.zhongchou.exception;

public class LoginAcctAlreadyInUseExceptionForUpdate extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public LoginAcctAlreadyInUseExceptionForUpdate(String message) {
		super(message);
	}
}
