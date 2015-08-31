package com.ebeijia.contro.exception;

/**
 * @author zhicheng.xu
 * @date 2015年4月23日
 */
public class SessionTimeoutException extends Exception {

	private static final long serialVersionUID = 1L;

	public SessionTimeoutException() {
		super();
	}

	public SessionTimeoutException(String msg) {
		super(msg);
	}

	public SessionTimeoutException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SessionTimeoutException(Throwable cause) {
		super(cause);
	}

}
