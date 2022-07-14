package io.shlink.userservice.exception;

public class ServiceDownException extends Exception {

	private static final long serialVersionUID = -5550242759016798250L;

	public ServiceDownException() {
		super();
	}

	protected ServiceDownException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceDownException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceDownException(String message) {
		super(message);
	}

	public ServiceDownException(Throwable cause) {
		super(cause);
	}

}
