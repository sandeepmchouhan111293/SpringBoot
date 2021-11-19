package com.infy.service;

import com.infy.dto.BookingDTO;
import com.infy.dto.CourierStatus;
import com.infy.exception.InfyCourierException;

public interface BookingService {
	
	public Integer bookCourier(BookingDTO details) throws InfyCourierException ;
	
	public Float calculateBookingAmount(Integer weight, String priority) throws InfyCourierException ;
	
	public BookingDTO getCourierDetail(Integer bookingId) throws InfyCourierException;
	
	public Integer updateCourierStatus(Integer bookingId, CourierStatus status) throws InfyCourierException;

}
