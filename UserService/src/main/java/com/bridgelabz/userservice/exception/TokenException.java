package com.bridgelabz.userservice.exception;

public class TokenException extends Exception{

private static final long serialVersionUID = 5460492644658485956L;
	
	private int errorCode;
	private String errorMsg;
	
	public TokenException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
