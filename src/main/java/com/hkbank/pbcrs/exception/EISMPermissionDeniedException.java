package com.hkbank.pbcrs.exception;

public class EISMPermissionDeniedException extends EismException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object interfaceMsg = null;

	public EISMPermissionDeniedException() {
		// TODO Auto-generated constructor stub
	}

	public EISMPermissionDeniedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EISMPermissionDeniedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EISMPermissionDeniedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public EISMPermissionDeniedException(String message, Object obj) {
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
