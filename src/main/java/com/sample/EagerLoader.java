package com.sample;

import org.springframework.stereotype.Component;

@Component
public class EagerLoader {

	public EagerLoader() {
		super();
		System.out.println(" EagerLoader Constructer is called");
	}

	
}
