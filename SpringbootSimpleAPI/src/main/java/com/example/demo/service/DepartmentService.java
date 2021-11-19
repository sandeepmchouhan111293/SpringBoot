package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Department;
import com.example.demo.exception.DepartmentNotFoundException;

public interface DepartmentService {

	public Department saveDepartments(Department department);

	public List<Department> fetchAllDepartmentList();

	public Department fetchDepartment(Long departmentId) throws DepartmentNotFoundException;

	public void deleteDepartment(Long departmentId);

	public Department updateDepartment(Long departmentId, Department department);

	public Department fetchDepartmentByName(String departmentName);

}
