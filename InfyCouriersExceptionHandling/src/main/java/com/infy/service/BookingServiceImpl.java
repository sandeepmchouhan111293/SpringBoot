package com.infy.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dto.BookingDTO;
import com.infy.dto.CourierStatus;
import com.infy.entity.Booking;
import com.infy.exception.InfyCourierException;
import com.infy.repository.BookingRepository;


@Service("bookingService")
@Transactional
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingRepository bookingRepository;
	
	@Override
	public Integer bookCourier(BookingDTO bookingDTO) throws InfyCourierException {
	
			Float bookingAmount = calculateBookingAmount(bookingDTO.getWeight(), bookingDTO.getPriority());
			bookingDTO.setBookingAmount(bookingAmount);
			bookingDTO.setStatus(CourierStatus.BOOKED);
			Booking booking = new Booking();
			booking.setBookingAmount(bookingDTO.getBookingAmount());
			booking.setBookingDate(bookingDTO.getBookingDate());
			booking.setDestination(bookingDTO.getDestination());
			booking.setPriority(bookingDTO.getPriority());
			booking.setWeight(bookingDTO.getWeight());
			booking.setSource(bookingDTO.getSource());
			booking.setBookingId(bookingDTO.getBookingId());
			booking.setStatus(bookingDTO.getStatus());
			booking = bookingRepository.save(booking);
			return booking.getBookingId();
			
				}
		
	
	
	public Float calculateBookingAmount(Integer weight, String priority) throws InfyCourierException {
		Float bookingAmount = 0f;
		if(weight<100) {
			bookingAmount=200f;
		}else if(weight<300) {
			bookingAmount=300f;
		}else if(weight<1000f){
			Float extraWeightCharge = (weight-300f)*2;
			bookingAmount=300f+extraWeightCharge;
		}else {
			bookingAmount=1500f;
		}
		
		if(priority=="HIGH") {
			bookingAmount+=100f;
		}else if(priority=="MEDIUM") {
			bookingAmount+=60f;
		}
		
		return bookingAmount;
	}

	@Override
	public BookingDTO getCourierDetail(Integer bookingId) throws InfyCourierException {
		Optional<Booking> optional = bookingRepository.findById(bookingId);
		Booking booking = optional.orElseThrow(() -> new InfyCourierException("Service.NO_RECORDS_FOUND"));
		
		
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setBookingAmount(booking.getBookingAmount());
		bookingDTO.setBookingDate(booking.getBookingDate());
		bookingDTO.setDestination(booking.getDestination());
		bookingDTO.setPriority(booking.getPriority());
		bookingDTO.setWeight(booking.getWeight());
		bookingDTO.setSource(booking.getSource());
		bookingDTO.setBookingId(booking.getBookingId());
		bookingDTO.setStatus(booking.getStatus());
		return bookingDTO;
	}

	@Override
	public Integer updateCourierStatus(Integer bookingId, CourierStatus courierStatus) throws InfyCourierException {
		Optional<Booking> optional = bookingRepository.findById(bookingId);
		Booking booking = optional.orElseThrow(() -> new InfyCourierException("Service.NO_RECORDS_FOUND"));
		

		booking.setStatus(courierStatus);
		return bookingId;
	}
	


}
