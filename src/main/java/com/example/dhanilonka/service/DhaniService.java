/**
 * 
 */
package com.example.dhanilonka.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dhanilonka.controller.AccessException;
import com.example.dhanilonka.model.Employee;
import com.example.dhanilonka.model.Response;
import com.example.dhanilonka.repository.DhaniRepository;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class DhaniService {
	
	Logger log=LoggerFactory.getLogger(DhaniService.class);
	
	List<Employee> list=new ArrayList<>();
	
	@Value("${response.timeout}")
	private double timedout;
	
	@Autowired
	private Employee empy;
	
	@Autowired
	private RestTemplate template;
	
	@Value("${seatallocation.url}")
	private String BASE_URL;
		
	@Autowired
	private DhaniRepository dhaniRepository;
	
	
	@CircuitBreaker(name="CircuitBreakerService",fallbackMethod ="serviceFallback")
	public ResponseEntity<Response> addEmployee(Employee emp) throws AccessException {
	
	    String uuid=UUID.randomUUID().toString();
	    double start= System.currentTimeMillis();
		
		list.add(emp);		
		empy.setEmpno(emp.getEmpno());		
		this.dhaniRepository.save(emp);
		ResponseEntity<String> seat=getSeat(emp);
		
		
		double end= System.currentTimeMillis();
		//log.info("Time taken by request: "+(end-start)+" ms");
		if((end-start)<timedout){	
			try{
				if(seat.getBody().equals("Unallocated"))
				{
					double endu= System.currentTimeMillis();
					log.info("Time taken by unallocation: "+(endu-start)+" ms");
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response(uuid,"Access to ODC denied","302",emp.getEmpno()));
				}
				double enda= System.currentTimeMillis();
				log.info("Time taken by allocation: "+(enda-start)+" ms");
							
				return ResponseEntity.status(HttpStatus.OK).body(new Response(uuid,"Access to ODC Granted Successfully","101",emp.getEmpno()));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		 
		throw new AccessException(new Response(uuid,"Access to ODC getting time-out","403",emp.getEmpno()));
	}
	
	 public ResponseEntity<Response> serviceFallback(Exception e)
     {
    	String uuid=UUID.randomUUID().toString();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(uuid,"Hystrix timed out while communication with seatallocation service","506",empy.getEmpno()));
   	    
     }

     public ResponseEntity<String> getSeat(Employee emp) {
    	 
    	 HttpHeaders header=new HttpHeaders();
    	 header.setContentType(MediaType.APPLICATION_JSON);
    	 
    	 HttpEntity<Employee> en=new HttpEntity<>(emp,header);
    	 ResponseEntity<String> res=template.postForEntity(BASE_URL, en, String.class);
    	 
    	 //log.info("Allocation status is "+res.getBody());
		return res;
	}
     
    
	public List<Employee> getEmployee() {
		return this.dhaniRepository.findAll();
	}
	
	
}
