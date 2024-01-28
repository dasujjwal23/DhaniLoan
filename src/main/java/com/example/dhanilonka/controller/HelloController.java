package com.example.dhanilonka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dhanilonka.model.Employee;
import com.example.dhanilonka.service.DhaniService;

@Controller
public class HelloController {

	@Autowired
	private DhaniService dservice;
	
	@GetMapping("/index")
	public String index()
	{
		return "index";
	}
	
	@GetMapping("/hello")
	public String hello(Model model,
			 @RequestParam(value="name", defaultValue="World") String name
			)
	{
		model.addAttribute("name",name);
		return "hello";
	}
	
	@GetMapping("/getEmployee")
	public String seat(Model model)
	{
		List<Employee> lists=this.dservice.getEmployee();
		model.addAttribute("lists",lists);
		return "seat";
	}
}
