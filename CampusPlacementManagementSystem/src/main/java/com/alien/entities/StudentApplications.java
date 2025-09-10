package com.alien.entities;



public class StudentApplications {
	
	private int id;
	private String name;
	private String email;
	private String rollno;
	private String branch;
	private String title;
	private String company;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRollno() {
		return rollno;
	}
	public void setRollno(String rollno) {
		this.rollno = rollno;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
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
	public StudentApplications(int id, String name, String email, String rollno, String branch, String title,
			String company, String status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.rollno = rollno;
		this.branch = branch;
		this.title = title;
		this.company = company;
		this.status = status;
	}
	
	
}
	