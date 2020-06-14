package com.cg.busbooking.booking.service;

import java.util.List;

import com.cg.busbooking.booking.entity.Booking;
import com.cg.busbooking.booking.exception.BookingIdNotFound;
import com.cg.busbooking.booking.exception.BookingNameNotFound;

public interface IBookingService {

	public List<Booking> getBookingDetails();

	public Booking getBookingById(int bookingId) throws BookingIdNotFound;

	public String deleteBookingById(int bookingId) throws BookingIdNotFound;

	public String updateBookingById(int bid, String phone) throws BookingIdNotFound;

	public String addBooking(Booking booking);

	public Booking getBooking(String name) throws BookingNameNotFound;
}
