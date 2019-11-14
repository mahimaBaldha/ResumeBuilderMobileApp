package com.builder.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "education", schema = "public")
public class Education {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String resume_id;
	private int edutype_id;
	
	
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
	public int getEdutype_id() {
		return edutype_id;
	}
	public void setEdutype_id(int edutype_id) {
		this.edutype_id = edutype_id;
	}
	@Override
	public String toString() {
		return "Education [id=" + id + ", resume_id=" + resume_id + ", edutype_id=" + edutype_id + "]";
	}

}
