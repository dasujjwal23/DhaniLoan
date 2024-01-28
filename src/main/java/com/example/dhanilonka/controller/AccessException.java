package com.example.dhanilonka.controller;

import com.example.dhanilonka.model.Response;

@SuppressWarnings("serial")
public class AccessException extends Exception{

	private Response resp;
	
	public AccessException(Response resp)
	{
		this.setResp(resp);
	}

	public Response getResp() {
		return resp;
	}

	public void setResp(Response resp) {
		this.resp = resp;
	}
}
