package com.cg.busbooking.booking.service;

import java.util.List;

import com.cg.busbooking.booking.dto.Booking;
import com.cg.busbooking.booking.entity.BookingEntity;
import com.cg.busbooking.booking.exception.BookingIdNotFound;
import com.cg.busbooking.booking.exception.BookingNameNotFound;

public interface IBookingService {

	public List<BookingEntity> getBookingDetails();

	public BookingEntity getBookingById(int bookingId) throws BookingIdNotFound;

	public String deleteBookingById(int bookingId) throws BookingIdNotFound;

	public String updateBookingById(int bid, String phone) throws BookingIdNotFound;

	public String addBooking(Booking booking);

	public BookingEntity getBooking(String name) throws BookingNameNotFound;
}
