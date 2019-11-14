package com.builder.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "skilltype", schema = "public")
public class SkillType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String area_of_interest;
	private String prog_lang;
	private String framework;
	private String tools;
	private String technologies;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArea_of_interest() {
		return area_of_interest;
	}
	public void setArea_of_interest(String area_of_interest) {
		this.area_of_interest = area_of_interest;
	}
	public String getProg_lang() {
		return prog_lang;
	}
	public void setProg_lang(String prog_lang) {
		this.prog_lang = prog_lang;
	}
	public String getFramework() {
		return framework;
	}
	public void setFramework(String framework) {
		this.framework = framework;
	}
	public String getTools() {
		return tools;
	}
	public void setTools(String tools) {
		this.tools = tools;
	}
	public String getTechnologies() {
		return technologies;
	}
	public void setTechnologies(String technologies) {
		this.technologies = technologies;
	}
	@Override
	public String toString() {
		return "SkillType [id=" + id + ", area_of_interest=" + area_of_interest + ", prog_lang=" + prog_lang
				+ ", framework=" + framework + ", tools=" + tools + ", technologies=" + technologies + "]";
	}

}
