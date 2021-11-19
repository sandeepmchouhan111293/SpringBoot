package com.infy.api;


import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.BookingDTO;
import com.infy.dto.CourierStatus;
import com.infy.exception.InfyCourierException;
import com.infy.service.BookingService;

@RestController
@RequestMapping("/infycourier")
@Validated
public class CourierBookingAPI {
	
	@Autowired
	private BookingService bookingService;
	 
	@Autowired
	private Environment environment;
	
	@PostMapping("/courier")
	public ResponseEntity<String> bookCourier(@Valid @RequestBody BookingDTO booking, Errors error) throws InfyCourierException {
		Integer bookingId = bookingService.bookCourier(booking);
		String message = environment.getProperty("API.BOOKING_SUCCESS")+" : "+bookingId;	
		return new ResponseEntity<String>(message,HttpStatus.CREATED);
	}

	@GetMapping(value="/courier/{bookingId}")
	public ResponseEntity<BookingDTO> getCourierDetails(@PathVariable @Min(value=0, message = "Booking id should be greater than 0") 
	@Max(value=99999, message = "Booking Id should be less than or equal to 5 digits")Integer bookingId) throws InfyCourierException {
		BookingDTO detail = bookingService.getCourierDetail(bookingId);
		return new ResponseEntity<BookingDTO>(detail,HttpStatus.OK);
	}
	
	@PutMapping("/courier/{bookingId}/{status}")
	public ResponseEntity<String> updateCourierStatus(@PathVariable @Min(value=0, message = "Booking id should be greater than 0") Integer bookingId, @PathVariable String status) throws InfyCourierException {
		CourierStatus courierStatus = Enum.valueOf(CourierStatus.class, status);
		bookingService.updateCourierStatus(bookingId,courierStatus); 
		String successMessage = environment.getProperty("API.STATUS_CHANGED");
		return new ResponseEntity<String>(successMessage+bookingId,HttpStatus.OK);	
	}

}

