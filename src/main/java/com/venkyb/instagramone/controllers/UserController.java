package com.venkyb.instagramone.controllers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import com.venkyb.instagramone.ServiceClass.ServiceClass;
import com.venkyb.instagramone.models.FollowersClass;
import com.venkyb.instagramone.models.PojoClass;
import com.venkyb.instagramone.repository.FollowersInterface;
import com.venkyb.instagramone.repository.RepoInterface;
import com.venkyb.instagramone.repository.postsInterface;



@RestController
public class UserController {
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	
	@Autowired
	RepoInterface repo;
	
	@Autowired
	postsInterface posts;
	
	@Autowired
	FollowersInterface followrepo;

	String currentUsername=new ServiceClass().getCurrentLoggedInUser();
	
	@RequestMapping("/")
	public String home() {
		logger.info("home page is received");
		return("just the home page nothing to see here");
	}
	
	@GetMapping("/users")
	public List<PojoClass> getUsers(){
		logger.info("get all users is requested");
		return repo.findAll();
	}

	@PostMapping("/adduser")
	public String addUser(PojoClass pojo){
		logger.info("adding a user is started");
		try {
			System.out.println(repo.getByUName(pojo.getUsername()).get(0).getUsername());
			logger.info("username already present in db");
			return("username already present, try another one");
		}
		catch(Exception e){
			logger.info("username is not present in db");
			pojo.setPassword(pojo.getPassword());
			repo.save(pojo);
			int uid = getUserId(pojo);
			addUserInFollowers(uid);
			logger.info("new user is created");
			return("user added");
			}	
	}

	private int getUserId(PojoClass pojo) {
		String uname = repo.getByUName(pojo.getUsername()).get(0).getUsername();
		int uid = repo.getByUName(uname).get(0).getId();
		System.out.println(uname);
		System.out.println(uid);
		return uid;
	}
	
	public void addUserInFollowers(int uid) {
		FollowersClass follower = buildFollower(uid);
		followrepo.save(follower);
		System.out.println("user added in followers table");
	}

	private FollowersClass buildFollower(int uid) {
		FollowersClass follower=new FollowersClass();
		follower.setUserid(uid);
		follower.setFollowingme("#");
		follower.setIamfollowing("#");
		return follower;
	}
	
	@GetMapping("/username/{uname}")
	public List<PojoClass> getUser(@PathVariable("uname") String uname) {
		return repo.getByUName(uname);
	}
	
	@GetMapping("/userid/{uid}")
	public Optional<PojoClass> getUser(@PathVariable("uid") int uid) {
		//return Optional.ofNullable(repo.getById(uid));
		//above two lines does the same thing
		return repo.findById(uid);
		
	}
	

	@DeleteMapping("/userid/{uid}")
	public String deleteUser(@PathVariable("uid") int uid) {
		repo.deleteById(uid);
		logger.info("the user "+uid+"is delted from db");
		return("user deleted");	
	}

	
	@Transactional
	@DeleteMapping("/username/{uname}")
	public String deleteUser(@PathVariable("uname") String uname) {
		repo.deleteByUsername(uname);
		logger.info("the user "+uname+"is delted from db");
		return("user deleted");
		
	}
	
	@PutMapping("/updateuser")
	public String updateUser(@RequestBody PojoClass pojo) {
		repo.save(pojo);
		return("user updated");
	}
		
}
