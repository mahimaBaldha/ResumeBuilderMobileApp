package com.builder.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.builder.api.model.Awards;
import com.builder.api.model.EduType;
import com.builder.api.model.Education;
import com.builder.api.model.Experience;
import com.builder.api.model.Interests;
import com.builder.api.model.Project;
import com.builder.api.model.ProjectType;
import com.builder.api.model.Resume;
import com.builder.api.model.Skill;
import com.builder.api.model.SkillType;
import com.builder.api.model.Users;
import com.builder.api.repository.AwardsRepository;
import com.builder.api.repository.EducationRepository;
import com.builder.api.repository.EducationTypesRepo;
import com.builder.api.repository.ExperienceRepository;
import com.builder.api.repository.InterestRepository;
import com.builder.api.repository.ProjectRepository;
import com.builder.api.repository.ProjectTypesRepo;
import com.builder.api.repository.ResumeRepository;
import com.builder.api.repository.SkillRepository;
import com.builder.api.repository.SkillsTypeRepo;
import com.builder.api.repository.UserRepository;

@Service
public class ResumeService {

	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private AwardsRepository awardsrepo;
	
	@Autowired
	private EducationRepository educationrepo;
	
	@Autowired
	private EducationTypesRepo edutyperepository;
	
	@Autowired
	private ExperienceRepository exprepo;
	
	@Autowired
	private InterestRepository intrepo;
	
	@Autowired
	private ProjectRepository projectrepo;
	
	@Autowired
	private ProjectTypesRepo projtyperepository;
	
	@Autowired
	private ResumeRepository resumerepo;
	
	@Autowired
	private SkillRepository skillrepo;
	
	@Autowired
	private SkillsTypeRepo skilltyperepository;
	
	public Optional<Users> getUserDetails(String id){
		Optional<Resume>resume = resumerepo.findById(id);
		return userrepository.findById(resume.get().getUser_id());
	}


	public List<Awards> getAwards(String id){
		
		List<Awards> aw = awardsrepo.findAll();
		List<Awards> list = new ArrayList<>();
		for(Awards ii: aw) {
			if(ii.getResume_id().equals(id)) {
				list.add(ii);
			}
		}
		return list;
	}
	
	public List<Experience> getExperience(String id){
		List<Experience> l = exprepo.findAll();
		List<Experience> list = new ArrayList<Experience>();
		
		for(Experience ii: l) {
			if(ii.getResume_id().equals(id)) {
				list.add(ii);
			}
		}
		return list;
	}
	
	public List<Interests> getInterests(String id){
		List<Interests> l = intrepo.findAll();
		List<Interests> list = new ArrayList<Interests>();
		
		for(Interests ii: l) {
			if(ii.getResume_id().equals(id)) {
				list.add(ii);
			}
		}
		return list;
	}
	
	public List<Optional<EduType>> getEducation(String id){
		
		List<Optional<EduType>> list = new ArrayList<>();
		List<Education> lm = educationrepo.findAll();
		
		for(Education ii: lm) {
			if(ii.getResume_id().equals(id)) {
				list.add(edutyperepository.findById(ii.getEdutype_id()));
			}
		}
		return list;
	}
	
	public List<Optional<ProjectType>> getProjects(String id){
		
		List<Optional<ProjectType>> list = new ArrayList<>();
		List<Project> lm = projectrepo.findAll();
		
		for(Project ii: lm) {
			if(ii.getResume_id().equals(id)) {
				list.add(projtyperepository.findById(ii.getProjtype_id()));
			}
		}
		return list;
	}
	
	public List<Optional<SkillType>> getSkills(String id){
		
		List<Optional<SkillType>> list = new ArrayList<>();
		List<Skill> lm = skillrepo.findAll();
		
		for(Skill ii: lm) {
			if(ii.getResume_id().equals(id)) {
				list.add(skilltyperepository.findById(ii.getSkilltype_id()));
			}
		}
		return list;
	}
	
	
	
	public Users addUserDetail(Users u1) {
		
		List<Users> list = userrepository.findAll();

		for(Users u : list) {
			if(u.isSession()) {
				u.setAddress(u1.getAddress());
				u.setDob(u1.getDob());
				userrepository.save(u);
				return u;
			}
		}
		
		return u1;
	}
	
	
	
	public Awards addAwards(String id,Awards awards) {
		return awardsrepo.save(awards);
	}
	
	public Interests addInterests(String id,Interests interest) {
		return intrepo.save(interest);
	}
	
	public Experience addExperience(String id, Experience exp) {
		return exprepo.save(exp);
	}
	
	public EduType addEducationDetails(String id, EduType edu) {
		
		edutyperepository.save(edu);
		
		Education education = new Education();
		education.setResume_id(id);
		education.setEdutype_id(edu.getId());
		educationrepo.save(education);
		
		return edu;
	}
	
	public ProjectType addProjectDetails(String id, ProjectType projects) {
		
		projtyperepository.save(projects);
		Project project = new Project();
		project.setResume_id(id);
		project.setProjtype_id(projects.getId());
		projectrepo.save(project);
			
		return projects;
	}
	
	public SkillType addSkillDetails(String id, SkillType skills) {
		
		skilltyperepository.save(skills);
		
		Skill skill = new Skill();
		skill.setResume_id(id);
		skill.setSkilltype_id(skills.getId());
		skillrepo.save(skill);
		
		return skills;
	}
	
	public boolean addResume(String id, Users user, Awards award, Interests interest, Experience experience, EduType education,  ProjectType projects, SkillType skills) {
		
		Users userDetail = addUserDetail(user);
		Awards awards = addAwards(id, award);
		Interests interests = addInterests(id, interest);
		Experience exp = addExperience(id, experience);
		EduType edu = addEducationDetails(id, education);
		ProjectType projtype = addProjectDetails(id, projects);
		SkillType skill = addSkillDetails(id, skills);
		
		if(userDetail != null && awards != null && interests != null && exp != null && edu != null && projtype != null && skill != null) {
			return true;
		}
		return false;
	}

	public Users editUser(Users u1) {
		
		List<Users> list = userrepository.findAll();

		for(Users u : list) {
			if(u.isSession()) {
				u.setAddress(u1.getAddress());
				u.setDob(u1.getDob());
				u.setName(u1.getName());
				userrepository.save(u);
				return u;
			}
		}
		
		return u1;
	}

	public Experience editExperience(String id, Experience experience) {
		List<Experience> list = exprepo.findAll();
		for(Experience exp : list) {
			if(exp.getResume_id().equals(id)) {
				exp.setExp(experience.getExp());
				exprepo.save(exp);
				return exp;
			}
		}
		return null;
	}


	public Awards editAward(String id, Awards awards) {
		List<Awards> list = awardsrepo.findAll();
		for(Awards award : list) {
			if(award.getResume_id().equals(id)) {
				award.setAwards(awards.getAwards());
				awardsrepo.save(award);
				return award;
			}
		}
		return null;
	}
	
	
	public Interests editInterest(String id, Interests interests) {
		List<Interests> list = intrepo.findAll();
		for(Interests interest : list) {
			if(interest.getResume_id().equals(id)) {
				interest.setInterest(interests.getInterest());
				intrepo.save(interest);
				return interest;
			}
		}
		return null;
	}


	public EduType editEducationDetails(String id, EduType edu_details) {
		List<Education> list = educationrepo.findAll();
		for(Education education : list) {
			if(education.getResume_id().equals(id)) {
				Optional<EduType> eduDetails = edutyperepository.findById(education.getEdutype_id());
				EduType edu = eduDetails.get();
				edu.setCpi(edu_details.getCpi());
				edu.setDegree(edu_details.getDegree());
				edu.setUniversity(edu_details.getUniversity());
				edu.setYear(edu_details.getYear());
				edutyperepository.save(edu);
				return edu;
			}
		}
		return null;
	}


	public ProjectType editProjectDetails(String id, ProjectType proj) {
		List<Project> list = projectrepo.findAll();
		for(Project Project : list) {
			if(Project.getResume_id().equals(id)) {
				Optional<ProjectType> projDetails = projtyperepository.findById(Project.getProjtype_id());
				ProjectType project = projDetails.get();
				project.setDescription(proj.getDescription());
				project.setDuration(proj.getDuration());
				project.setMentor(proj.getMentor());
				project.setTeam_size(proj.getTeam_size());
				project.setTitle(proj.getTitle());
				projtyperepository.save(project);
				return project;
			}
		}
		return null;
	}


	public SkillType editSkillDetails(String id, SkillType skills) {
		List<Skill> list = skillrepo.findAll();
		for(Skill Skill : list) {
			if(Skill.getResume_id().equals(id)) {
				Optional<SkillType> skillDetails = skilltyperepository.findById(Skill.getSkilltype_id());
				SkillType skill = skillDetails.get();
				skill.setArea_of_interest(skills.getArea_of_interest());
				skill.setFramework(skills.getFramework());
				skill.setProg_lang(skills.getProg_lang());
				skill.setTechnologies(skills.getTechnologies());
				skill.setTools(skills.getTools());
				skilltyperepository.save(skill);
				return skill;
			}
		}
		return null;
	}
	
	// DELETE 
	
	
	public void deleteProject(int id) {
		
		List<Project> list = projectrepo.findAll();
		for(Project project: list) {
			if(project.getProjtype_id() == id) {
				projectrepo.deleteById(project.getId());
			}
		}
		projtyperepository.deleteById(id);
		
	}
	
	
	
}
