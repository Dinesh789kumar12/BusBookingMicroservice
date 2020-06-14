/****************************************************************************************************************************
 - Class Name        : BookingServiceImpl
 - Author           :  Dinesh Kumar
 - Creation Date    : 11-06-2020
 - Description      : This is service class to do all kind of operation from dto class
  ****************************************************************************************************************************/

package com.cg.busbooking.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.busbooking.booking.dao.BookingRepository;
import com.cg.busbooking.booking.dto.Booking;
import com.cg.busbooking.booking.entity.BookingEntity;
import com.cg.busbooking.booking.exception.BookingIdNotFound;
import com.cg.busbooking.booking.exception.BookingNameNotFound;

@Service
public class BookingServiceImpl implements IBookingService {

	@Autowired
	private BookingRepository repository;

	/****************************************************************************************************************************
	 * - Method Name : getBookingDetails() - Input Parameters : - Return type :
	 * Booking - Creation Date : 10-06-2020 - Description : To get all detail Bus
	 * booking information from the database.
	 ****************************************************************************************************************************/
	public List<BookingEntity> getBookingDetails() {
		return repository.findAll();
	}

	/****************************************************************************************************************************
	 * - Method Name : getBookingById - Input Parameters : BookingDto bookingId -
	 * Return type : Booking - Creation Date : 10-06-2020 - Description : To get
	 * specific detail booking by bookingId from the database.
	 ****************************************************************************************************************************/
	public BookingEntity getBookingById(int bookingId) throws BookingIdNotFound {
		Optional<BookingEntity> optional = repository.findById(bookingId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new BookingIdNotFound("Booking is not found given Id");
		}
	}

	/****************************************************************************************************************************
	 * - Method Name : deleteBookingById - Input Parameters : BookingDto bookingId -
	 * Return type : String - Creation Date : 10-06-2020 - Description : To delete
	 * Bus booking by bookingId from the database.
	 ****************************************************************************************************************************/
	public String deleteBookingById(int bookingId) throws BookingIdNotFound {
		Optional<BookingEntity> optional = repository.findById(bookingId);
		if (optional.isPresent()) {
			repository.deleteById(bookingId);
		} else {
			throw new BookingIdNotFound("Booking is not found given Id");
		}
		return "Deleted Sucessfully ";
	}

	/****************************************************************************************************************************
	 * - Method Name : updateBookingById - Input Parameters : BookingDto bid,phone -
	 * Return type : String - Creation Date : 10-06-2020 - Description : To update
	 * phone number by bookingId from the booking table.
	 ****************************************************************************************************************************/
	public String updateBookingById(int bid, String phone) throws BookingIdNotFound {
		Optional<BookingEntity> optional = repository.findById(bid);
		BookingEntity booking = null;
		if (optional.isPresent()) {
			booking = optional.get();
			booking.setPhone(phone);
			repository.save(booking);
		} else {
			throw new BookingIdNotFound("Booking not found for given id");
		}
		return "Phone number is updated Successfully";
	}

	/****************************************************************************************************************************
	 * - Method Name : addBooking - Input Parameters : BookingDto booking - Return
	 * type : String - Creation Date : 10-06-2020 - Description : Inserting the
	 * Booking information entered by admin into the database.
	 ****************************************************************************************************************************/
	public String addBooking(Booking booking) {
		BookingEntity entity = new BookingEntity();
		entity.setAge(booking.getCustomerAge());
		entity.setBookingId(booking.getbId());
		entity.setBusId(booking.getBusId());
		entity.setGender(booking.getGender());
		entity.setPhone(booking.getPhone_no());
		entity.setTravelDate(booking.getDate());
		entity.setUserId(booking.getuId());
		repository.saveAndFlush(entity);
		return "Booking has been accepted";
	}

	/****************************************************************************************************************************
	 * - Method Name : getBooking - Input Parameters : BookingDto name - Return type
	 * : Booking - Creation Date : 10-06-2020 - Description : To get specific
	 * details of booking by name from the database
	 ****************************************************************************************************************************/
	public BookingEntity getBooking(String name) throws BookingNameNotFound {
		BookingEntity booking = repository.getBookingDetails(name);
		if (booking == null) {
			throw new BookingNameNotFound("Booking not found for given name");
		}
		return booking;
	}
}
