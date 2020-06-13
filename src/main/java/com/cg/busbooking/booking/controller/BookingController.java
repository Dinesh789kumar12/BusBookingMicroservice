package com.cg.busbooking.booking.controller;

import java.util.HashSet;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.busbooking.booking.entity.Booking;
import com.cg.busbooking.booking.exception.BookingIdNotFound;
import com.cg.busbooking.booking.exception.BookingNameNotFound;
import com.cg.busbooking.booking.service.BookingService;

@RestController
@RequestMapping(value = "/booking")
@Validated
public class BookingController {
	@Autowired
	BookingService service;

	// http://localhost:9095/booking/bookingdetails
	@GetMapping("/bookingdetails")
	public HashSet<Booking> getAllRouteDetails() {
		return (HashSet<Booking>) service.getBookingDetails();
	}

	// http://localhost:9095/booking/bookingdetailsbyId
	@GetMapping("/bookingdetailsbyId/{bookingId}")
	public Booking getBookingById(@PathVariable("bookingId") @Min(1000) @Max(2000) int bookingId)
			throws BookingIdNotFound {
		return service.getBookingById(bookingId);
	}

	// http://localhost:9095/booking/delete
	@DeleteMapping("/delete/{bookingId}")
	public ResponseEntity<String> deleteRoute(@PathVariable("bookingId") int bookingId) throws BookingIdNotFound {
		return ResponseEntity.ok(service.deleteBookingById(bookingId));
	}

	// http://localhost:9095/booking/
	@PostMapping("/")
	public void addBooking(@Valid @RequestBody Booking booking) {
		service.addBooking(booking);
	}

	// http://localhost:9095/booking/fetchbookingByname
	@GetMapping("/fetchbookingByname/{name}")
	public ResponseEntity<Booking> getBooking(@PathVariable("name") String name) throws BookingNameNotFound {
		return new ResponseEntity<Booking>(service.getBooking(name), HttpStatus.ACCEPTED);
	}

	// http://localhost:9095/booking/updatebookingById
	@GetMapping("/updatebookingById/{bid}/{phone}")
	public String updateBookingById(@PathVariable("bid") int bid, @PathVariable("phone") long phone)
			throws BookingIdNotFound {
		return service.updateBookingById(bid, phone);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<String> handleConstraintViolation(Exception e) {
		return new ResponseEntity<>(": " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
