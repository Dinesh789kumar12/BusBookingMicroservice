package com.cg.busbooking.booking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.busbooking.booking.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	@Query("Select book from Booking book where book.name=?1")
	public Booking getBookingDetails(String name);
}
