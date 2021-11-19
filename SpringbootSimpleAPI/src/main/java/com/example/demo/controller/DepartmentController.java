package com.example.demo.controller;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Department;
import com.example.demo.exception.DepartmentNotFoundException;
import com.example.demo.service.DepartmentService;

@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	
	@PostMapping("/departments")
	public Department saveDepartments(@Valid @RequestBody Department department) {
		LOGGER.info("Inside Save Department");
		return departmentService.saveDepartments(department);
	}
	
	@GetMapping("/departments")
	public List<Department> fetchDepartmentList() {
		LOGGER.info("Inside Fetch Department List");
		return departmentService.fetchAllDepartmentList();
	}
	
	@GetMapping("/departments/{id}")
	public Department fetchDepartment(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
		LOGGER.info("Inside Fetch Department"+departmentId);
		return departmentService.fetchDepartment(departmentId);
	}
	
	@DeleteMapping("/departments/{id}")
	public String deleteDepartment(@PathVariable("id") Long departmentId) {
		 departmentService.deleteDepartment(departmentId);
		 return "Department with id "+departmentId+" successfuly deleted";
	}
	
	@PutMapping("/departments/{id}")
	public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department) {
		return departmentService.updateDepartment(departmentId, department);
	}
	
	@GetMapping("/departments/name/{name}")
	public Department fetchDepartmentByName(@PathVariable("name") String departmentName) {
		return departmentService.fetchDepartmentByName(departmentName);
	}
}
