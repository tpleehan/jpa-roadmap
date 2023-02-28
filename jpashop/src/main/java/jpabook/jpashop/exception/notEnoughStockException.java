package jpabook.jpashop.exception;

public class notEnoughStockException extends RuntimeException {

	public notEnoughStockException() {
		super();
	}

	public notEnoughStockException(String message) {
		super(message);
	}

	public notEnoughStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public notEnoughStockException(Throwable cause) {
		super(cause);
	}

	protected notEnoughStockException(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
