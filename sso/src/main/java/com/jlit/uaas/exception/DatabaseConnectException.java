package com.jlit.uaas.exception;

/**
 * 数据库连接异常
 *
 * @author cici
 */
public class DatabaseConnectException extends RuntimeException {

	public DatabaseConnectException() {
		super();
	}

	/**
	 * 调用Exception类中的构造方法
	 *
	 * @param msg 外部传递进来的参数，表示异常信息
	 */
	public DatabaseConnectException(String msg) {
		super(msg);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DatabaseConnectException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public DatabaseConnectException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
