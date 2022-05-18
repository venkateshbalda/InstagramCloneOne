package com.venkyb.instagramone.ControllerClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.venkyb.instagramone.RepoInterface.FollowersInterface;
import com.venkyb.instagramone.RepoInterface.RepoInterface;
import com.venkyb.instagramone.RepoInterface.postsInterface;
import com.venkyb.instagramone.PojoClass.*;
import com.venkyb.instagramone.ControllerClass.*;
import com.venkyb.instagramone.ServiceClass.*;
@RestController
public class ControllerClass {
	
	@Autowired
	RepoInterface repo;
	
	@Autowired
	postsInterface posts;
	
	@Autowired
	FollowersInterface followrepo;

	String currentUsername=new ServiceClass().getCurrentLoggedInUser();
	
	@RequestMapping("/")
	public String home() {
		return("juat the home page nothing to see here");
	}
	
	@GetMapping("/users")
	public List<PojoClass> getUsers(){
		return repo.findAll();
	}

	@PostMapping("/adduser")
	public String addUser(PojoClass pojo){
		try {
			System.out.println(repo.getByUName(pojo.getUsername()).get(0).getUsername());
			return("user already present, try another one");
		}
		catch(Exception e){
			repo.save(pojo);
			String uname = repo.getByUName(pojo.getUsername()).get(0).getUsername();
			int uid = repo.getByUName(uname).get(0).getId();
			System.out.println(uname);
			System.out.println(uid);
			addUserInFollowers(uid);
			return("user added");
			}	
	}
	
	public void addUserInFollowers(int uid) {
		FollowersClass user=new FollowersClass();
		user.setUserid(uid);
		user.setFollowingme("#");
		user.setIamfollowing("#");
		followrepo.save(user);
		System.out.println("user added in followers table");
	}
	
	@GetMapping("/username/{uname}")
	public List<PojoClass> getUser(@PathVariable("uname") String uname) {
		return repo.getByUName(uname);
	}
	
	@GetMapping("/userid/{uid}")
	public Optional<PojoClass> getUser(@PathVariable("uid") int uid) {
		return repo.findById(uid);
		//return Optional.ofNullable(repo.getById(uid));
		//above two lines does the same thing
	}
	

	@DeleteMapping("/userid/{uid}")
	public String deleteUser(@PathVariable("uid") int uid) {
		repo.deleteById(uid);
		return("user deleted");	
	}

	
	@Transactional
	@DeleteMapping("/username/{uname}")
	public String deleteUser(@PathVariable("uname") String uname) {
		repo.deleteByUsername(uname);
		return("user deleted");
		
	}
	
	@PutMapping("/updateuser")
	public PojoClass updateUser(@RequestBody PojoClass pojo) {
		repo.save(pojo);
		return pojo;
	}
		
}
