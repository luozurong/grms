package com.hori.db.exception;

/**
 * DAO中数据删除失败异常
 *
 * @author cici
 */
public class DataDeleteFailException extends RuntimeException {

	public DataDeleteFailException() {
		super();
	}

	/**
	 * 调用Exception类中的构造方法
	 *
	 * @param msg 外部传递进来的参数，表示异常信息
	 */
	public DataDeleteFailException(String msg) {
		super(msg);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataDeleteFailException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public DataDeleteFailException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
