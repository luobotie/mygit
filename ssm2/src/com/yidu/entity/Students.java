package com.yidu.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Students implements Serializable{
	private  int stuId;
	private  String stuName;
	private  int stuAge;
	private String stuAddress;
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public int getStuAge() {
		return stuAge;
	}
	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}
	public String getStuAddress() {
		return stuAddress;
	}
	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress;
	}
	public Students() {
		super();
	}
	public Students(String stuName, int stuAge, String stuAddress) {
		super();
		this.stuName = stuName;
		this.stuAge = stuAge;
		this.stuAddress = stuAddress;
	}
	

}
