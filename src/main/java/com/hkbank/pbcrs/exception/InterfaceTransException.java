package com.hkbank.pbcrs.exception;

public class InterfaceTransException extends EismException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1693954582739188394L;
	private Object interfaceMsg = null;

	public InterfaceTransException() {
		// TODO Auto-generated constructor stub
	}

	public InterfaceTransException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InterfaceTransException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InterfaceTransException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InterfaceTransException(String message, Object obj) {
		super(message, obj);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Object getInterfaceMsg() {
		return interfaceMsg;
	}

	public void setInterfaceMsg(Object interfaceMsg) {
		this.interfaceMsg = interfaceMsg;
	}

}
