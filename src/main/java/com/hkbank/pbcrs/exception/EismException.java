package com.hkbank.pbcrs.exception;


public class EismException extends Exception {
	
	private Object obj = null;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4591486967615930633L;

	public EismException() {
	}

	public EismException(String message, Throwable cause) {
		super(message, cause);
	}

	public EismException(String message) {
		super(message);
	}

	public EismException(Throwable cause) {
		super(cause);
	}
	
	public EismException(String message, Object obj) {
		super(message);
		this.obj = obj;
	}

	public Object getObject() {
		return this.obj;
	}
}
