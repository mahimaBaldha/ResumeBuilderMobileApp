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
	
	
	
	public Awards addAwards(Awards awards) {
		return awardsrepo.save(awards);
	}
	
	public Interests addInterests(Interests interest) {
		return intrepo.save(interest);
	}
	
	public Experience addExperience(Experience exp) {
		return exprepo.save(exp);
	}
	
	public EduType addEducationDetails(EduType edu) {
		List<Users> list = userrepository.findAll();
		Users user = new Users();
		for(Users u : list) {
			if(u.isSession()) {
				user = u;
				break;
			}
		}
		edutyperepository.save(edu);
		List<Resume> list1 = resumerepo.findAll();
		
		for(Resume r  : list1) {
			if(r.getUser_id().equals(user.getId())) {
				Education education = new Education();
				education.setResume_id(r.getId());
				education.setEdutype_id(edu.getId());
				educationrepo.save(education);
			}
		}
		return edu;
	}
	
	public ProjectType addProjectDetails(ProjectType projects) {
		List<Users> list = userrepository.findAll();
		Users user = new Users();
		for(Users u : list) {
			if(u.isSession()) {
				user = u;
				break;
			}
		}
		projtyperepository.save(projects);
		List<Resume> list1 = resumerepo.findAll();
		
		for(Resume r  : list1) {
			if(r.getUser_id().equals(user.getId())) {
				Project project = new Project();
				project.setResume_id(r.getId());
				project.setProjtype_id(projects.getId());
				projectrepo.save(project);
			}
		}
		return projects;
	}
	
	public SkillType addSkillDetails(SkillType skills) {
		List<Users> list = userrepository.findAll();
		Users user = new Users();
		for(Users u : list) {
			if(u.isSession()) {
				user = u;
				break;
			}
		}
		skilltyperepository.save(skills);
		List<Resume> list1 = resumerepo.findAll();
		
		for(Resume r  : list1) {
			if(r.getUser_id().equals(user.getId())) {
				Skill skill = new Skill();
				skill.setResume_id(r.getId());
				skill.setSkilltype_id(skills.getId());
				skillrepo.save(skill);
			}
		}
		return skills;
	}
	
	public boolean addResume(Users user, Awards award, Interests interest, Experience experience, EduType education,  ProjectType projects, SkillType skills) {
		
		Users userDetail = editUser(user);
		Awards awards = addAwards(award);
		Interests interests = addInterests(interest);
		Experience exp = addExperience(experience);
		EduType edu = addEducationDetails(education);
		ProjectType projtype = addProjectDetails(projects);
		SkillType skill = addSkillDetails(skills);
		
		if(userDetail != null && awards != null && interests != null && exp != null && edu != null && projtype != null && skill != null) {
			return true;
		}
		return false;
	}
	
}
