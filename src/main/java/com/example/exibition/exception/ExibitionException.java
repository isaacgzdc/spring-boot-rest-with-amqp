package com.example.exibition.exception;

public class ExibitionException extends Exception {
	
	public ExibitionException(String msg) {
		super(msg);
	}

	public ExibitionException(String msg, Exception e) {
		super(msg,e);
	}
	
	private static final long serialVersionUID = 4449176646624847455L;

}
