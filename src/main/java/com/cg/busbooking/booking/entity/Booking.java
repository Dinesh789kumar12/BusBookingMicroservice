package com.cg.busbooking.booking.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Booking {
	@Id
	@Column(name = "booking_Id")
	private int bookingId;
	@Column(name = "user_Id")
	private int userId;
	@Column(name = "bus_Id")
	private int busId;
	@NotNull(message = "Name cannnot be null")
	@NotBlank(message = "Name cannot be blank")
	private String name;
	@Min(value = 2, message = "Age cannot be smaller than 2")
	@Max(value = 60, message = "Age cannot be larger than 60")
	private int age;
	@NotNull(message = "Gender is required either it is Male/Female")
	private String gender;
	@NotNull(message = "Phone number cannot be null")
	private long phone;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate travelDate;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Booking() {
		super();
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public LocalDate getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}

	public Booking(int bookingId, int userId, int busId, String name, int age, String gender, long phone,
			LocalDate travelDate) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
		this.busId = busId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.travelDate = travelDate;
	}
}
