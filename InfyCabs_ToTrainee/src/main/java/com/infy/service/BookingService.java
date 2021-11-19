package com.infy.service;

import java.util.List;

import com.infy.dto.CabBookingDTO;
import com.infy.exception.InfyCabException;

public interface BookingService {

		public Integer bookCab(CabBookingDTO booking) throws InfyCabException;
		public List<CabBookingDTO> getBookingDetails(Long mobileNo) throws InfyCabException;
		public Integer cancelBooking(Integer bookingId) throws InfyCabException;
	
}
