package com.builder.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "experience", schema = "public")
public class Experience {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String resume_id;
	private String experience;
	
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
	public String getExp() {
		return experience;
	}
	public void setExp(String exp) {
		this.experience = exp;
	}
	@Override
	public String toString() {
		return "Experience [id=" + id + ", resume_id=" + resume_id + ", exp=" + experience + "]";
	}
	
}
