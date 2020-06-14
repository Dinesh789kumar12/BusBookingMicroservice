/****************************************************************************************************************************
 - File Name        : BookingController
 - Author           : Dinesh Kumar
 - Creation Date    : 11-06-2020
 - Description      : This is endpoint controller to use and to support Busbooking Microservice
  ****************************************************************************************************************************/

package com.cg.busbooking.booking.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.busbooking.booking.entity.Booking;
import com.cg.busbooking.booking.exception.BookingIdNotFound;
import com.cg.busbooking.booking.exception.BookingNameNotFound;
import com.cg.busbooking.booking.service.IBookingService;

@RestController
@RequestMapping(value = "/booking")
@Validated
public class BookingController {
	@Autowired
	private IBookingService service;

	/****************************************************************************************************************************
	 * - Method Name : getAllBookingDetails() - Return type : List<Booking> - -
	 * Description : To retrieve list of booking details from Booking Database -
	 * End-point : http://localhost:9095/booking/bookingdetails
	 ****************************************************************************************************************************/
	@GetMapping("/bookingdetails")
	public List<Booking> getAllBookingDetails() {
		return service.getBookingDetails();
	}

	/****************************************************************************************************************************
	 * - Method Name : getBookingById - Input Parameters : BookingDto bookingId -
	 * Return type : Booking - Description : To get Booking Detail by Id from
	 * database -End-point : http://localhost:9095/booking/bookingdetailsbyId
	 ****************************************************************************************************************************/
	@GetMapping("/bookingdetailsbyId/{bookingId}")
	public Booking getBookingById(@PathVariable("bookingId") @Min(1000) @Max(2000) int bookingId)
			throws BookingIdNotFound {
		return service.getBookingById(bookingId);
	}

	/****************************************************************************************************************************
	 * - Method Name : deleteBookingById - Input Parameters : BookingDto bookingId -
	 * - Return type : ResponseEntity<String> - Description : To delete booking by
	 * id - End-point : http://localhost:9095/booking/delete
	 ****************************************************************************************************************************/
	@DeleteMapping("/delete/{bookingId}")
	public ResponseEntity<String> deleteBookingById(@PathVariable("bookingId") int bookingId) throws BookingIdNotFound {
		return ResponseEntity.ok(service.deleteBookingById(bookingId));
	}

	/****************************************************************************************************************************
	 * - Method Name : addBooking - Input Parameters : booking - Return type : -
	 * ResponseEntity<String> - Description : To add booking into the database -
	 * End-point : http://localhost:9095/booking/
	 ****************************************************************************************************************************/
	@PostMapping("/")
	public ResponseEntity<String> addBooking(@Valid @RequestBody Booking booking) {
		return ResponseEntity.ok(service.addBooking(booking));
	}

	/****************************************************************************************************************************
	 * - Method Name : getBookingByName - Input Parameters : BookingDto bookingId -
	 * Return type : ResponseEntity<Booking> - Description : To get Booking By Name
	 * -End-point : http://localhost:9095/booking/fetchbookingByname
	 ****************************************************************************************************************************/
	@GetMapping("/fetchbookingByname/{name}")
	public ResponseEntity<Booking> getBookingByName(@PathVariable("name") String name)
			throws BookingNameNotFound {
		return new ResponseEntity<>(service.getBooking(name), HttpStatus.ACCEPTED);
	}

	/****************************************************************************************************************************
	 * - Method Name : updatebookingById - Input Parameters : BookingDto
	 * bookingId,phone - Return type : ResponseEntity<String> - Description : To
	 * update phone number of booking by bid -End-point :
	 * http://localhost:9095/booking/updatebookingById
	 ****************************************************************************************************************************/
	@PutMapping("/updatebookingById/{bid}/{phone}")
	public ResponseEntity<String> updateBookingById(@PathVariable("bid") int bid, @PathVariable("phone") String phone)
			throws BookingIdNotFound {
		return ResponseEntity.ok(service.updateBookingById(bid, phone));
	}

	/****************************************************************************************************************************
	 * - Method Name : handleConstraintViolation - Return type :
	 * ResponseEntity<String> - Description : To handle unwanted exceptionwith
	 * javax-validation constraint
	 ****************************************************************************************************************************/
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<String> handleConstraintViolation(ConstraintViolationException e) {
		return new ResponseEntity<>(": " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
