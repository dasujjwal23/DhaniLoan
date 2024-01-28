package com.sample;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class Guide {

	@PostConstruct
	public void home()
	{
		System.out.println(" post constructor is called");
	}
	
	public void getdata()
	{
		System.out.println(" Method will be called with the help of Bean");
	}
}
