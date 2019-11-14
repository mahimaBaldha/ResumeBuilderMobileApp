package com.builder.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.builder.api.model.Awards;
import com.builder.api.model.EduType;
import com.builder.api.model.Education;
import com.builder.api.model.Experience;
import com.builder.api.model.Interests;
import com.builder.api.model.Output;
import com.builder.api.model.Project;
import com.builder.api.model.ProjectType;
import com.builder.api.model.Resume;
import com.builder.api.model.SkillType;
import com.builder.api.model.Users;
import com.builder.api.repository.ResumeRepository;
import com.builder.api.repository.UserRepository;
import com.builder.api.service.ResumeService;
import com.builder.api.service.UserService;

@RestController
@RequestMapping("/resume")
public class ResumeController {
	
	static Output op = new Output();
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private ResumeService resumeservice;
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private ResumeRepository resumerepo;
	
	
	
//	get requests
	
	
	
	@GetMapping(path= "/getEducation/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getUserPage(@PathVariable("id") String id) {
		
		List<Optional<EduType>> list = resumeservice.getEducation(id);
		if(list == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("No education details added");
		}else {
			op.setError(false);
			op.setMessage("success");
			op.setData(list);
		}
		return new ResponseEntity<Object>(op, HttpStatus.OK); 
	}
	
	@GetMapping(path= "/getAwards/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getAwards(@PathVariable("id") String id) {
		
		List<Awards> list = resumeservice.getAwards(id);
		if(list == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("No Awards added");
		}
		else {
			op.setError(false);
			op.setMessage("success");
			op.setData(list);
		}
		return new ResponseEntity<Output>(op, HttpStatus.OK); 
	}
	
	@GetMapping(path= "/getInterests/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getInterests(@PathVariable("id") String id) {
		
		List<Interests> list = resumeservice.getInterests(id);
		if(list == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("No Interests added");
		}
		else {
			op.setError(false);
			op.setMessage("success");
			op.setData(list);
		}
		return new ResponseEntity<Output>(op, HttpStatus.OK); 
	}
	
	
	@GetMapping(path= "/getExperience/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getExperience(@PathVariable("id") String id) {
		
		List<Experience> list = resumeservice.getExperience(id);
		if(list == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("No Experience added");
		}
		else {
			op.setError(false);
			op.setMessage("success");
			op.setData(list);
		}
		return new ResponseEntity<Output>(op, HttpStatus.OK); 
	}
	
	@GetMapping(path= "/getProjects/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getProjectPage(@PathVariable("id") String id) {
		
		List<Optional<ProjectType>> list = resumeservice.getProjects(id);
		if(list == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("No project details added");
		}else {
			op.setError(false);
			op.setMessage("success");
			op.setData(list);
		}
		return new ResponseEntity<Object>(op, HttpStatus.OK); 
	}
	
	@GetMapping(path= "/getResume/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getResumeDetails(@PathVariable("id") String id) {
		
		Optional<Resume> opt = resumerepo.findById(id);
		if(!opt.isPresent()) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("Build your resume first");
			return new ResponseEntity<Object>(op, HttpStatus.NOT_FOUND);
		}
		
		Optional<Resume>resume = resumerepo.findById(id);
		
		String user = resume.get().getUser_id();
		Optional<Users> u = userrepository.findById(user);
		if(!u.get().isSession()) {
		
			op.setError(true);
			op.setMessage("not success");
			op.setData("Login First");
			return new ResponseEntity<Object>(op, HttpStatus.OK); 
		}
		
		List<Object> list = new ArrayList<Object>();
		
		u = resumeservice.getUserDetails(id);
		
		list.add(u);
		
		if(u.get().isOnetime()) {
			u.get().setOnetime(false);
			userrepository.save(u.get());
		}
		list.add(resumeservice.getEducation(id));
		list.add(resumeservice.getSkills(id));
		list.add(resumeservice.getProjects(id));
		list.add(resumeservice.getExperience(id));
		list.add(resumeservice.getAwards(id));
		list.add(resumeservice.getInterests(id));
		
		op.setError(false);
		op.setMessage("success");
		op.setData(list);
		
		return new ResponseEntity<Output>(op, HttpStatus.OK); 
	}
	
	
	// ADD REQUESTS
	
	
	@PostMapping(path= "/addResume", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addResumeDetails(@RequestBody Users user, @RequestBody Awards award,
			@RequestBody Interests interest, @RequestBody Experience experience , @RequestBody EduType education,
		    @RequestBody ProjectType projects , @RequestBody SkillType skills) {
		
		 if(resumeservice.addResume(user, award, interest, experience, education, projects, skills)) {
			 	op.setError(false);
				op.setMessage("success");
				op.setData("Data saved");
			 return new ResponseEntity<Output>(op, HttpStatus.OK); 
		 }
		
		 	op.setError(true);
			op.setMessage("not success");
			op.setData("please add appropriate data");
		return new ResponseEntity<Output>(op, HttpStatus.OK); 
	}
	
	@PostMapping(path= "/addAwards", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addAwardDetails(@RequestBody Awards awards){
			Awards award = resumeservice.addAwards(awards);
		if(award == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("data is not added");
			return new ResponseEntity<Output>(op, HttpStatus.NOT_FOUND);
		}
		op.setError(false);
		op.setMessage("success");
		op.setData(award);
		return new ResponseEntity<Output>(op, HttpStatus.OK);
	}
	
	@PostMapping(path= "/addInterests", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addInterestDetails(@RequestBody Interests interest){
		Interests interests = resumeservice.addInterests(interest);
		if(interests == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("data is not added");
			return new ResponseEntity<Output>(op, HttpStatus.NOT_FOUND);
		}
		op.setError(false);
		op.setMessage("success");
		op.setData(interests);
		return new ResponseEntity<Output>(op, HttpStatus.OK);
		
	}
	
	@PostMapping(path= "/addExperience", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addExperienceDetails(@RequestBody Experience exp){
		Experience experience = resumeservice.addExperience(exp);
		if(experience == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("data is not added");
			return new ResponseEntity<Output>(op, HttpStatus.NOT_FOUND);
		}
		op.setError(false);
		op.setMessage("success");
		op.setData(experience);
		return new ResponseEntity<Output>(op, HttpStatus.OK);
		
	}
	
	@PostMapping(path= "/addEducation", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addEducationDetails(@RequestBody EduType education){
		EduType educations = resumeservice.addEducationDetails(education);
		if(educations == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("data is not added");
			return new ResponseEntity<Output>(op, HttpStatus.NOT_FOUND);
		}
		op.setError(false);
		op.setMessage("success");
		op.setData(educations);
		return new ResponseEntity<Output>(op, HttpStatus.OK);
	}
	
	@PostMapping(path= "/addProject", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addProjectDetails(@RequestBody ProjectType project){
		ProjectType projects = resumeservice.addProjectDetails(project);
		if(projects == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("data is not added");
			return new ResponseEntity<Output>(op, HttpStatus.NOT_FOUND);
		}
		op.setError(false);
		op.setMessage("success");
		op.setData(projects);
		return new ResponseEntity<Output>(op, HttpStatus.OK);
	}
	
	@PostMapping(path= "/addSkill", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addSkillDetails(@RequestBody SkillType skill){
		SkillType skills = resumeservice.addSkillDetails(skill);
		if(skills == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("data is not added");
			return new ResponseEntity<Output>(op, HttpStatus.NOT_FOUND);
		}
		op.setError(false);
		op.setMessage("success");
		op.setData(skills);
		return new ResponseEntity<Output>(op, HttpStatus.OK);
	}
	
//	@PostMapping(path= "/getResume/{id}", consumes = "application/json", produces = "application/json")
//	public ResponseEntity<?> addExperienceDetails(@PathVariable("id") String id ,@RequestBody Experience experience){
//		Experience experience = resumeservice.addExperience(id, experience);
//		
//		return new ResponseEntity<Experience>(experience, HttpStatus.OK);
//	}

}
