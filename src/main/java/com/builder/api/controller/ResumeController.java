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
			op.setData("Experience not added");
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
			op.setData("project-details not added");
		}else {
			op.setError(false);
			op.setMessage("success");
			op.setData(list);
		}
		return new ResponseEntity<Object>(op, HttpStatus.OK); 
	}
	
	@GetMapping(path= "/getSkills/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getSkillPage(@PathVariable("id") String id) {
		
		List<Optional<SkillType>> list = resumeservice.getSkills(id);
		if(list == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("skill details not added");
		}else {
			op.setError(false);
			op.setMessage("success");
			op.setData(list);
		}
		return new ResponseEntity<Object>(op, HttpStatus.OK); 
	}
	
	@GetMapping(path= "/getResume/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getResumeDetails(@PathVariable("id") String id) {
		
		Optional<Resume> resume = resumerepo.findById(id);
		
		if(!resume.isPresent()) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("Build your resume first");
			return new ResponseEntity<Object>(op, HttpStatus.NOT_FOUND);
		}
		
		List<Object> list = new ArrayList<Object>();
		
		Optional<Users> u = resumeservice.getUserDetails(id);
		
		list.add(u);
		try {
			list.add(resumeservice.getEducation(id));
			list.add(resumeservice.getSkills(id));
			list.add(resumeservice.getProjects(id));
			list.add(resumeservice.getExperience(id));
			list.add(resumeservice.getAwards(id));
			list.add(resumeservice.getInterests(id));
		}
		catch(Exception ex) {
			op.setError(true);
			op.setMessage("not success");
			op.setData(list);
		}
		
		op.setError(false);
		op.setMessage("success");
		op.setData(list);
		
		return new ResponseEntity<Output>(op, HttpStatus.OK); 
	}
	
	
	@GetMapping(path= "/getOneProject/{resume_id}/{proj_id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getOneProjectPage(@PathVariable("resume_id") String r_id, @PathVariable("proj_id") int p_id) {
		
		List<Optional<ProjectType>> list = resumeservice.getProjects(r_id);
		Optional<ProjectType> list2 = resumeservice.getProject(list, p_id);
		if(list == null || list2 == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("no data available");
		}else {
			op.setError(false);
			op.setMessage("success");
			op.setData(list2);
		}
		return new ResponseEntity<Object>(op, HttpStatus.OK); 
	}
	
	
	// ADD REQUESTS
	
	@PostMapping(path= "/addUserDetail/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addUserDetails(@PathVariable("id") String id, @RequestBody Users users){
		Users user = resumeservice.addUserDetail(id, users);
		if(user == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("data is not added");
			return new ResponseEntity<Output>(op, HttpStatus.NOT_FOUND);
		}
		op.setError(false);
		op.setMessage("success");
		op.setData(user);
		return new ResponseEntity<Output>(op, HttpStatus.OK);
	}
	
	
	
//	@PostMapping(path= "/addResume/{id}", consumes = "application/json", produces = "application/json")
//	public ResponseEntity<?> addResumeDetails(@PathVariable("id") String id,@RequestBody Users user, @RequestBody Awards award,
//			@RequestBody Interests interest, @RequestBody Experience experience , @RequestBody EduType education,
//		    @RequestBody ProjectType projects , @RequestBody SkillType skills) {
//		
//		 if(resumeservice.addResume(id, user, award, interest, experience, education, projects, skills)) {
//			 	op.setError(false);
//				op.setMessage("success");
//				op.setData("Data saved");
//				
//			 return new ResponseEntity<Output>(op, HttpStatus.OK); 
//		 }
//		
//		 	op.setError(true);
//			op.setMessage("not success");
//			op.setData("please add appropriate data");
//		return new ResponseEntity<Output>(op, HttpStatus.OK); 
//	}
	
	@PostMapping(path= "/addAwards/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addAwardDetails(@PathVariable("id") String id, @RequestBody Awards awards){
			Awards award = resumeservice.addAwards(id, awards);
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
	
	@PostMapping(path= "/addInterests/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addInterestDetails(@PathVariable("id") String id, @RequestBody Interests interest){
		Interests interests = resumeservice.addInterests(id, interest);
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
	
	@PostMapping(path= "/addExperience/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addExperienceDetails(@PathVariable("id") String id, @RequestBody Experience exp){
		Experience experience = resumeservice.addExperience(id, exp);
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
	
	@PostMapping(path= "/addEducation/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addEducationDetails(@PathVariable("id") String id,@RequestBody EduType education){
		EduType educations = resumeservice.addEducationDetails(id, education);
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
	
	@PostMapping(path= "/addProject/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addProjectDetails(@PathVariable("id") String id, @RequestBody ProjectType project){
		ProjectType projects = resumeservice.addProjectDetails(id, project);
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
	
	@PostMapping(path= "/addSkill/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addSkillDetails(@PathVariable("id") String id, @RequestBody SkillType skill){
		SkillType skills = resumeservice.addSkillDetails(id, skill);
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
	
	
	// EDIT DATA 
	
	@PostMapping(path= "/editUserDetail/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> editUserDetails(@PathVariable("id") String id ,@RequestBody Users user){
		Users users = resumeservice.editUser(id, user);
		if(users == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("data is not edited");
			return new ResponseEntity<Output>(op, HttpStatus.NOT_FOUND);
		}
		op.setError(false);
		op.setMessage("success");
		op.setData(users);
		return new ResponseEntity<Output>(op, HttpStatus.OK);
	}
	
	@PostMapping(path= "/editExperience/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> editExperienceDetails(@PathVariable("id") String id ,@RequestBody Experience experience){
		Experience exp = resumeservice.editExperience(id, experience);
		if(exp == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("data is not edited");
			return new ResponseEntity<Output>(op, HttpStatus.NOT_FOUND);
		}
		op.setError(false);
		op.setMessage("success");
		op.setData(exp);
		return new ResponseEntity<Output>(op, HttpStatus.OK);
	}
	
	@PostMapping(path= "/editAwards/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> editAwardsDetails(@PathVariable("id") String id ,@RequestBody Awards award){
		Awards awards = resumeservice.editAward(id, award );
		
		if(awards == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("data is not edited");
			return new ResponseEntity<Output>(op, HttpStatus.NOT_FOUND);
		}
		op.setError(false);
		op.setMessage("success");
		op.setData(awards);
		return new ResponseEntity<Output>(op, HttpStatus.OK);
	}
	
	@PostMapping(path= "/editInterest/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> editInterestDetails(@PathVariable("id") String id ,@RequestBody Interests Interest){
		Interests interest = resumeservice.editInterest(id, Interest );
		
		if(interest == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("data is not edited");
			return new ResponseEntity<Output>(op, HttpStatus.NOT_FOUND);
		}
		op.setError(false);
		op.setMessage("success");
		op.setData(interest);
		return new ResponseEntity<Output>(op, HttpStatus.OK);
	}
	
	
	
	@PostMapping(path= "/editEducation/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> editEducationDetails(@PathVariable("id") String id ,@RequestBody EduType edu_details){
		EduType education = resumeservice.editEducationDetails(id, edu_details );
		
		if(education == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("data is not edited");
			return new ResponseEntity<Output>(op, HttpStatus.NOT_FOUND);
		}
		op.setError(false);
		op.setMessage("success");
		op.setData(education);
		return new ResponseEntity<Output>(op, HttpStatus.OK);
	}
	
	
	@PostMapping(path= "/editProject/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> editProjectDetails(@PathVariable("id") String id ,@RequestBody ProjectType proj_details){
		ProjectType project = resumeservice.editProjectDetails(id, proj_details );
		
		if(project == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("data is not edited");
			return new ResponseEntity<Output>(op, HttpStatus.NOT_FOUND);
		}
		op.setError(false);
		op.setMessage("success");
		op.setData(project);
		return new ResponseEntity<Output>(op, HttpStatus.OK);
	}
	
	
	@PostMapping(path= "/editSkill/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> editSkillDetails(@PathVariable("id") String id ,@RequestBody SkillType skill){
		SkillType skills = resumeservice.editSkillDetails(id, skill);
		
		if(skills == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("data is not edited");
			return new ResponseEntity<Output>(op, HttpStatus.NOT_FOUND);
		}
		op.setError(false);
		op.setMessage("success");
		op.setData(skills);
		return new ResponseEntity<Output>(op, HttpStatus.OK);
	}

	@GetMapping(path= "/deleteProject/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> deleteProjectpage(@PathVariable("id") int id) {
		
		resumeservice.deleteProject(id);
		op.setError(false);
		op.setMessage("success");
		op.setData("Project deleted successfully");
		
		return new ResponseEntity<Output>(op, HttpStatus.OK); 
	}
}
