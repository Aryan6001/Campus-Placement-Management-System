package com.alien.entities;


public class Application  {
	private String title;
	private String company;
	private String status;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Application(String title, String company, String status) {
		
		this.title = title;
		this.company = company;
		this.status = status;
	}
	
}
	
