package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	//@RequestMapping(value="/",method=RequestMethod.GET) OR below one
	@GetMapping("/")
	public String hello() {
		return "Welcome to Spring Boot";
	}
}
