package com.builder.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "interest", schema = "public")
public class Interests {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String resume_id;
	private String interest;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getResume_id() {
		return resume_id;
	}
	public void setResume_id(String resume_id) {
		this.resume_id = resume_id;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interests) {
		this.interest = interests;
	}
	@Override
	public String toString() {
		return "Interests [id=" + id + ", resume_id=" + resume_id + ", interest=" + interest + "]";
	}
	
}
