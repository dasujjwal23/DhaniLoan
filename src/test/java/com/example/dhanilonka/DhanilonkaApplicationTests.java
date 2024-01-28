package com.example.dhanilonka;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

//import org.junit.jupiter.api.Test;
import org.junit.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
//import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;

import com.example.dhanilonka.controller.AccessException;
import com.example.dhanilonka.model.Employee;
import com.example.dhanilonka.model.Response;
//import com.example.dhanilonka.model.Response;
import com.example.dhanilonka.repository.DhaniRepository;
import com.example.dhanilonka.service.DhaniService;

@SpringBootTest
@RunWith(value = SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
public class DhanilonkaApplicationTests {

	@Autowired
	public DhaniService dservice;
	
	@MockBean
	public DhaniRepository dhaniRepository;
	
	@Test
	public void getEmployee(){
		when(dhaniRepository.findAll()).thenReturn(
				Stream.of(new Employee(107775,"Rakesh kumar",12000.00),new Employee(107774,"Santosh kumar",18000.00))
				.collect(Collectors.toList())
				);
		assertEquals(2,dservice.getEmployee().size());
	}
	
	@Test
	public void addEmployee() throws AccessException
	{
		Employee emp=new Employee(107885,"Kiran kumar",22000.00);
		Response res=new Response("UUID1","Access to ODC Granted Successfully","101",emp.getEmpno());
		when(this.dhaniRepository.save(emp)).thenReturn(emp);
		assertEquals(res.getEmpno(),dservice.addEmployee(emp).getBody().getEmpno());
		
		
		switch(dservice.addEmployee(emp).getStatusCode().toString())
		{
		 case "200": assertEquals("Access to ODC Granted Successfully",dservice.addEmployee(emp).getBody().getDescription());
		             break;	
		             
		 case "403": assertEquals("Access to ODC denied",dservice.addEmployee(emp).getBody().getDescription());
		             break;
		             
		 case "408": assertEquals("Access to ODC getting time-out",dservice.addEmployee(emp).getBody().getDescription());
                     break;
                     
		 case "500": assertEquals("Hystrix timed out while communication with seatallocation service",dservice.addEmployee(emp).getBody().getDescription());
                      break; 
		 default:
			  break;
		}
	}

}
