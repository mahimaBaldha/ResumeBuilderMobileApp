package com.builder.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project", schema = "public")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String resume_id;
	private int projtype_id;
	
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
	public int getProjtype_id() {
		return projtype_id;
	}
	public void setProjtype_id(int projtype_id) {
		this.projtype_id = projtype_id;
	}
	@Override
	public String toString() {
		return "Project [id=" + id + ", resume_id=" + resume_id + ", projtype_id=" + projtype_id + "]";
	}
	
}
