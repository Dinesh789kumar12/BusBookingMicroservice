package com.cg.busbooking.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.busbooking.booking.dao.BookingRepository;
import com.cg.busbooking.booking.entity.Booking;
import com.cg.busbooking.booking.exception.BookingIdNotFound;
import com.cg.busbooking.booking.exception.BookingNameNotFound;

@Service
public class BookingServiceImpl implements IBookingService {

	@Autowired
	BookingRepository repository;

	public List<Booking> getBookingDetails() {
		return repository.findAll();
	}

	public Booking getBookingById(int bookingId) throws BookingIdNotFound {
		Optional<Booking> optional = repository.findById(bookingId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new BookingIdNotFound("Booking is not found given Id");
		}
	}

	public String deleteBookingById(int bookingId) throws BookingIdNotFound {
		Optional<Booking> optional = repository.findById(bookingId);
		if (optional.isPresent()) {
			repository.deleteById(bookingId);
			return "Deleted Sucessfully ";
		} else {
			throw new BookingIdNotFound("Booking is not found given Id");
		}
	}

	public String updateBookingById(int bid, long phone) throws BookingIdNotFound {
		Optional<Booking> optional = repository.findById(bid);
		Booking booking = null;
		if (optional.isPresent()) {
			booking = optional.get();
			booking.setPhone(phone);
			repository.save(booking);
			return "Phone number is updated Successfully";
		} else {
			throw new BookingIdNotFound("Booking not found for given id");
		}
	}

	public void addBooking(Booking booking) {
		repository.save(booking);
	}

	public Booking getBooking(String name) throws BookingNameNotFound {
		Booking booking = repository.getBookingDetails(name);
		if (booking == null) {
			throw new BookingNameNotFound("Booking not found for given name");
		}
		return booking;
	}
}
