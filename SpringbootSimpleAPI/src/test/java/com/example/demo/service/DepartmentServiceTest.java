package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

@SpringBootTest
class DepartmentServiceTest {

	@Autowired
	private DepartmentService departmentService;
	
	@MockBean
	private DepartmentRepository departmentRepository;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	public void whenValidDepartmentName_thenDepartmentShouldFound() {
		String departmentName="FS-ADM";
		
		Department department = departmentService.fetchDepartmentByName(departmentName);
		
		assertEquals(departmentName, department.getDepartmentName());
	}
}
