package com.stock.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class UserImplements {
	
 

	private String first_name;
	
	private String last_name;
	
	private String user_id;
	

	private String email;
	

	private Date dob;
	
	private String password;

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
					return dob;
	}

 

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
