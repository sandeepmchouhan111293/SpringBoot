package com.infy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.infy.entity.CabBooking;

public interface BookingRepository extends CrudRepository<CabBooking, Integer>{
	
	List<CabBooking> findByUserMobile(Long userMobile);

}
