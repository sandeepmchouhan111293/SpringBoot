package com.example.demo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.exception.DepartmentNotFoundException;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department saveDepartments(Department department) {
		// TODO Auto-generated method stub
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> fetchAllDepartmentList() {
		return departmentRepository.findAll();
	}

	@Override
	public Department fetchDepartment(Long departmentId) throws DepartmentNotFoundException {
		
		Optional<Department> department =departmentRepository.findById(departmentId);
		
		if(!department.isPresent()) {
			throw new DepartmentNotFoundException("Department Not Available");
		}
		else {
			return department.get();
		}
		//return departmentRepository.findById(departmentId).get();
	}

	@Override
	public void deleteDepartment(Long departmentId) {
		departmentRepository.deleteById(departmentId);	
	}

	@Override
	public Department updateDepartment(Long departmentId, Department department) {
		Department temporarydepartment = departmentRepository.findById(departmentId).get();
		
		if(Objects.nonNull(department.getDepartmentName()) 
				&& !"".equalsIgnoreCase(department.getDepartmentName())) {
			temporarydepartment.setDepartmentName(department.getDepartmentName());
		}
		
		if(Objects.nonNull(department.getDepartmentName()) 
				&& !"".equalsIgnoreCase(department.getDepartmentAddress())) {
			temporarydepartment.setDepartmentAddress(department.getDepartmentAddress());
		}
		
		if(Objects.nonNull(department.getDepartmentName()) 
				&& !"".equalsIgnoreCase(department.getDepartmentCode())) {
			temporarydepartment.setDepartmentCode(department.getDepartmentCode());
		}
		return departmentRepository.save(temporarydepartment);
	}

	
	@Override
	public Department fetchDepartmentByName(String departmentName) {
		
		return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
	}

}
