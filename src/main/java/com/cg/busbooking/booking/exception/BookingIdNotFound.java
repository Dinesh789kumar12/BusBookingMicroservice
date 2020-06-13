package com.cg.busbooking.booking.exception;

public class BookingIdNotFound extends Exception {
	/**
	 * This class is used for exception purpose When there is id is not available so
	 * our service not shut down and all service run perfectly.
	 */
	private static final long serialVersionUID = 1L;
	final String msg;

	public BookingIdNotFound(String sg) {
		super();
		this.msg = sg;
	}

	@Override
	public String getMessage() {
		return msg;
	}
}
