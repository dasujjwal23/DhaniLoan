package com.example.dhanilonka.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.dhanilonka.model.Response;

@RestControllerAdvice
public class DhaniControllerAdvice {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
	public Response home(AccessException exp)
	{
		Response resp=new Response();
		resp.setAccessCode(exp.getResp().getAccessCode());
		resp.setAccessId(exp.getResp().getAccessId());
		resp.setDescription(exp.getResp().getDescription());
		resp.setEmpno(exp.getResp().getEmpno());
		
		return resp;	
	}
}
