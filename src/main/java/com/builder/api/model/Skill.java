package com.builder.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "skill", schema = "public")
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String resume_id;
	private int skilltype_id;
	
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
	public int getSkilltype_id() {
		return skilltype_id;
	}
	public void setSkilltype_id(int skilltype_id) {
		this.skilltype_id = skilltype_id;
	}
	@Override
	public String toString() {
		return "Skill [id=" + id + ", resume_id=" + resume_id + ", skilltype_id=" + skilltype_id + "]";
	}
	
}
