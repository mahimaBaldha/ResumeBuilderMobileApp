package com.builder.api.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resume", schema = "public")
public class Resume {

	@Id
	private String id;
	private String user_id;
//	private Users user;
//	private Awards awards;
//	private Experience experience;
//	private Interests interests;
//	private List<SkillType> skills;
//	private List<ProjectType> projects;
//	private List<EduType> educations;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Resume [id=" + id + ", user_id=" + user_id +  "]";
	}
	
	
}
