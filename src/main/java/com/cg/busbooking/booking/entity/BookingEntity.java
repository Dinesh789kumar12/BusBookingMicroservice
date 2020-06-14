package com.cg.busbooking.booking.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class BookingEntity {
	@Id
	@Column(name = "booking_Id")
	@Min(1000)@Max(2000)
	@NotNull(message = "Booking Id has not been null")
	private int bookingId;
	@Column(name = "user_Id")
	private int userId;
	@Column(name = "bus_Id")
	private int busId;
	@NotNull(message = "Name cannnot be null")
	@NotBlank(message = "Name is Mandatory")
	@Size(min = 3,max=10)
	@Pattern(regexp = "^[A-Za-z]*",message = "Name field Should not have Special Characters\nName Cant have Numeric Values")
	private String name;
	@Min(value = 2, message = "Age cannot be smaller than 2")
	@Max(value = 60, message = "Age cannot be larger than 60")
	private int age;
	@NotNull(message = "Gender is required either it is Male/Female")
	@NotEmpty(message = "Gender field Should not be empty")
	@Pattern(regexp = "[A-Za-z]*",message = " Gender field Should not have Special Characters  Gender field Should not have numbers")
	private String gender;
	@NotBlank(message = "Phone number cannot be blank")
	@Size(min = 10,max = 10)
	@Pattern(regexp="(^$|[0-9]{10})")
	private  String phone;
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull @Future
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}
}
