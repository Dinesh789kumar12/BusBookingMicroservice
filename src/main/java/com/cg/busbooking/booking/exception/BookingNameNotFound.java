package com.cg.busbooking.booking.exception;

public class BookingNameNotFound extends Exception {

	private static final long serialVersionUID = 1L;
	final String msg;

	public BookingNameNotFound(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}
}
