package com.builder.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "projecttype", schema = "public")
public class ProjectType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	private String title;
	private String duration;
	private String mentor;
	private String team_size;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getMentor() {
		return mentor;
	}
	public void setMentor(String mentor) {
		this.mentor = mentor;
	}
	public String getTeam_size() {
		return team_size;
	}
	public void setTeam_size(String team_size) {
		this.team_size = team_size;
	}
	@Override
	public String toString() {
		return "ProjectType [id=" + id + ", description=" + description + ", title=" + title + ", duration=" + duration
				+ ", mentor=" + mentor + ", team_size=" + team_size + "]";
	}
	
	
}
