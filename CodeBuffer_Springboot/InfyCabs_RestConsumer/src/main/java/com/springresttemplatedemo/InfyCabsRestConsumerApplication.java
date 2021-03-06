package com.springresttemplatedemo;

import java.time.LocalDate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.springresttemplatedemo.dto.CabBookingDTO;

@SpringBootApplication
public class InfyCabsRestConsumerApplication implements CommandLineRunner{

	//Logger
	private static final Log LOGGER = LogFactory.getLog(InfyCabsRestConsumerApplication.class);
	
	//Rest Template Declaration
	private static RestTemplate restTemplate = new RestTemplate();
	
	//Endpoints
	private static final String Cancel_Booking ="http://localhost:8765/cancel/{bookingId}";
	
	private static final String Get_Booking_Details_By_MobileNo ="http://localhost:8765/{mobileNo}";
	
	private static final String Book_Cab ="http://localhost:8765/";
		
	public static void main(String[] args) {
		SpringApplication.run(InfyCabsRestConsumerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * CabBookingDTO cabBookingDTO = new CabBookingDTO();
		 * cabBookingDTO.setSource("Santa Monica");
		 * cabBookingDTO.setDestination("California");
		 * cabBookingDTO.setFare((float)500);
		 * cabBookingDTO.setTravelDate(LocalDate.of(2021, 11, 17));
		 * cabBookingDTO.setUserMobile((long) 789654123); cabBookingDTO.setStatus('A');
		 * bookCab(cabBookingDTO);
		 */		
		//getBookingDetails((long)789654123);
		cancelBooking((Integer)4);
	}

	public void bookCab(CabBookingDTO cabBookingDTO) {
		String message =restTemplate.postForObject(Book_Cab, cabBookingDTO, String.class);
		LOGGER.info(message);
	}
	
	public void getBookingDetails(Long mobileNo) {
		CabBookingDTO []cabBookingDTO1 =restTemplate.getForObject(Get_Booking_Details_By_MobileNo, CabBookingDTO[].class, mobileNo);
		for(CabBookingDTO cabBookingDTO:cabBookingDTO1)
		LOGGER.info(cabBookingDTO);
	}
	
	public void cancelBooking(Integer bookingId) {	
		restTemplate.put(Cancel_Booking,String.class,bookingId);
	}
}
