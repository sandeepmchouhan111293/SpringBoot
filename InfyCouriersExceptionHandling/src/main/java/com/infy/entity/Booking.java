package com.infy.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.infy.dto.CourierStatus;



@Entity

public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer bookingId;
	private Integer weight;
	private LocalDate bookingDate;
	private String source;
	private String destination;
	private String priority;
	private Float bookingAmount;
	
	@Enumerated(EnumType.STRING)
	private CourierStatus status;
	
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Float getBookingAmount() {
		return bookingAmount;
	}
	public void setBookingAmount(Float bookingAmount) {
		this.bookingAmount = bookingAmount;
	}
	public CourierStatus getStatus() {
		return status;
	}
	public void setStatus(CourierStatus status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
	return 121;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (this.getBookingId() == null) {
			if (other.getBookingId() != null)
				return false;
		} else if (!this.getBookingId().equals(other.getBookingId()))
			return false;
		return true;
	}
	
	
}
