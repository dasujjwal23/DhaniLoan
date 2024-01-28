package com.example.dhanilonka.controller;

//import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
//import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dhanilonka.model.Employee;
import com.example.dhanilonka.model.Response;
import com.example.dhanilonka.service.DhaniService;

@RestController
@RequestMapping("/private/v2")
public class DhaniController {
	
	@Autowired
	private DhaniService dservice;
		
	@PostConstruct
	public void home()
	{
		System.out.println(" To get Started....");
	}
	
	@GetMapping("/getEmployee")
	public List<Employee> getEmployees()
	{
		return dservice.getEmployee();
	}
	
	@PostMapping("/addEmployee")
	public ResponseEntity<Response> addEmployee(@RequestBody Employee emp) throws AccessException
	{
		if(!emp.equals(null)) {
		  return dservice.addEmployee(emp);
		} 
		throw new NullPointerException("Object Not Found");
	}
}
