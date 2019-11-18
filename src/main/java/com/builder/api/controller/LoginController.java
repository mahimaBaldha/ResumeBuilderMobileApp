package com.builder.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.builder.api.model.Output;
import com.builder.api.model.Resume;
import com.builder.api.model.Users;
import com.builder.api.repository.ResumeRepository;
import com.builder.api.repository.UserRepository;
import com.builder.api.service.UserService;

@RestController
@RequestMapping("/user")
public class LoginController {

	static String resume_id;
	static Output op = new Output();
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private ResumeRepository resumerepo;
	
	
	@GetMapping(path= "/getResumeId", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getResumeId() {
		String id;
		String loggedinuser= null;
		List<Users> users = userrepository.findAll();
		for(Users user: users) {
			if(user.isSession()) {
				loggedinuser = user.getId();
				break;
			}
		}
		if(loggedinuser != null) {
			List<Resume> resumes = resumerepo.findAll();
			for(Resume r : resumes) {
				if(r.getUser_id().equals(loggedinuser)) {
					id = r.getId();
					
					op.setError(false);
					op.setMessage("success");
					op.setData(id);
					return new ResponseEntity<Output>(op, HttpStatus.OK); 
				}
			}
		}
		op.setError(true);
		op.setMessage("not success");
		op.setData("login first");
		return new ResponseEntity<Output>(op, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(path= "/signup", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> registerPage(@RequestBody Users user) {

		Optional<Users> u = userrepository.findById(user.getId());
		if(u.isPresent()) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("User already exists");
			return new ResponseEntity<Output>(op, HttpStatus.CONFLICT);
		}
		
		Users users = userservice.saveUser(user);
		op.setError(false);
		op.setMessage("success");
		op.setData(users);
		return new ResponseEntity<Output>(op, HttpStatus.OK);
	}
	
	
	@PostMapping(path= "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> loginPage(@RequestBody Users user) {

		Optional<Users> u = userrepository.findById(user.getId());
		if(!u.isPresent()) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("User doesn't exists");
			return new ResponseEntity<Output>(op, HttpStatus.NOT_FOUND);
		}
		
		String pass = user.getPassword();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
		
		if (encoder.matches(pass, u.get().getPassword()) == false) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("Invalid password");
			return new ResponseEntity<String>("Invalid password", HttpStatus.NOT_FOUND);
		}
		
		if(u.get().isOnetime()) {
			resume_id =  UUID.randomUUID().toString();
			userservice.BuildResume(resume_id, u.get());
		}
		
		List<Resume> resume = resumerepo.findAll();
		u.get().setOnetime(false);
		u.get().setSession(true);
		userrepository.save(u.get());
		for(Resume r : resume) {
			if(r.getUser_id().equals(u.get().getId())) {
				op.setError(false);
				op.setMessage("success");
				op.setData(r);
				return new ResponseEntity<Output>(op, HttpStatus.OK);
			}
		}
		op.setError(false);
		op.setMessage("success");
		op.setData(u.get());
		return new ResponseEntity<Output>(op, HttpStatus.OK);
	}
	
	@GetMapping(path= "/logout", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> logoutFunciton() {
		List<Users> users = userrepository.findAll();
		for(Users user : users) {
			if(user.isSession()) {
				user.setSession(false);
				userrepository.save(user);
			}
		}
		op.setError(false);
		op.setMessage("success");
		op.setData("Logged out!");
		return new ResponseEntity<Output>(op, HttpStatus.OK);
	}
	
	
	@GetMapping(path= "/getUserById/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getUserPage(@PathVariable("id") String id) {
		
		Optional<Resume> resumeDetails = resumerepo.findById(id);
		
		Optional<Users> u = userrepository.findById(resumeDetails.get().getUser_id());
		if(!u.isPresent()) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("User doesn't exists");
			return new ResponseEntity<Output>(op, HttpStatus.NOT_FOUND);
		}
		op.setError(false);
		op.setMessage("success");
		op.setData(u.get());
		return new ResponseEntity<Output>(op, HttpStatus.OK); 
	}
	
	@GetMapping(path= "/getUsers", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getUsers() {
	
		List<Users> users = userrepository.findAll();
		
		if(users == null) {
			op.setError(true);
			op.setMessage("not success");
			op.setData("No users found");
			return new ResponseEntity<Output>(op, HttpStatus.NOT_FOUND);
		}
		op.setError(false);
		op.setMessage("success");
		op.setData(users);
		return new ResponseEntity<Output>(op, HttpStatus.OK); 
	}
	
}
