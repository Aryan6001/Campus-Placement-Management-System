package com.alien.entities;


public class Job {
	private int id;
	private String title;
	private String company;
	private String location;
	private int ctc;
	private double cgpa;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getCtc() {
		return ctc;
	}
	public void setCtc(int ctc) {
		this.ctc = ctc;
	}
	public double getCgpa() {
		return cgpa;
	}
	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}
	public String getDescription() {
		return description;
	}
	public void setDiscription(String description) {
		this.description = description;
	}
	public Job(int id, String title, String company, String location, int ctc, double cgpa, String description) {
		
		this.id = id;
		this.title = title;
		this.company = company;
		this.location = location;
		this.ctc = ctc;
		this.cgpa = cgpa;
		this.description = description;
	}
	
	
}
	