package com.example.dhanilonka.model;

import org.springframework.stereotype.Component;

@Component
public class Response {

	private String AccessId;
	private String Description;
	private String AccessCode;
	private int empno;
	
	public void setEmpno(int empno)
	{
		this.empno=empno;
	}
	
	public int getEmpno()
	{
		return empno;
	}
	public String getAccessId() {
		return AccessId;
	}
	public void setAccessId(String accessId) {
		AccessId = accessId;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getAccessCode() {
		return AccessCode;
	}
	public void setAccessCode(String accessCode) {
		AccessCode = accessCode;
	}
	public Response(String accessId, String description, String accessCode, int empno) {
		super();
		AccessId = accessId;
		Description = description;
		AccessCode = accessCode;
		this.empno=empno;
	}
	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
