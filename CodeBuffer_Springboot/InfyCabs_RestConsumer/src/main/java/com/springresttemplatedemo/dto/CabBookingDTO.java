package com.springresttemplatedemo.dto;

import java.time.LocalDate;
import java.util.Objects;

public class CabBookingDTO {

	private Integer	bookingId;
	private String source;
	private String destination;
	private Float fare;
	private LocalDate travelDate;
	private Long userMobile;
	private Character status;
	
	
	public CabBookingDTO(Integer bookingId, String source, String destination, Float fare, LocalDate travelDate,
			Long userMobile, Character status) {
		super();
		this.bookingId = bookingId;
		this.source = source;
		this.destination = destination;
		this.fare = fare;
		this.travelDate = travelDate;
		this.userMobile = userMobile;
		this.status = status;
	}

	public CabBookingDTO() {
		// TODO Auto-generated constructor stub
	}

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

	public Float getFare() {
		return fare;
	}

	public void setFare(Float fare) {
		this.fare = fare;
	}

	public LocalDate getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}

	public Long getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(Long userMobile) {
		this.userMobile = userMobile;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CabBookingDTO [bookingId=" + bookingId + ", source=" + source + ", destination=" + destination
				+ ", fare=" + fare + ", travelDate=" + travelDate + ", userMobile=" + userMobile + ", status=" + status
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookingId, destination, fare, source, status, travelDate, userMobile);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CabBookingDTO other = (CabBookingDTO) obj;
		return Objects.equals(bookingId, other.bookingId) && Objects.equals(destination, other.destination)
				&& Objects.equals(fare, other.fare) && Objects.equals(source, other.source)
				&& Objects.equals(status, other.status) && Objects.equals(travelDate, other.travelDate)
				&& Objects.equals(userMobile, other.userMobile);
	}
	
}
