package com.infybank.repository;

import org.springframework.data.repository.CrudRepository;

import com.infybank.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
