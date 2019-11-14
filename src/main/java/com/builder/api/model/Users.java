package com.builder.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "user", schema = "public")
public class Users {

	@Id
	private String id;
	private String password;
	private String name;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dob;
	private String address;
	private boolean onetime;
	private boolean session;

	public boolean isSession() {
		return session;
	}

	public void setSession(boolean session) {
		this.session = session;
	}

	public boolean isOnetime() {
		return onetime;
	}

	public void setOnetime(boolean onetime) {
		this.onetime = onetime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}

	public Users() {
		
	}
	
	public Users(String id, String pass,  String name, String roll_id, Date dob, String address) {
		super();
		this.id = id;
		this.password = pass;
		this.name = name;
		this.dob = dob;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", password=" + password + ", name=" + name + ", dob=" + dob + ", address=" + address
				+ ", onetime=" + onetime + "]";
	}	
	
}
