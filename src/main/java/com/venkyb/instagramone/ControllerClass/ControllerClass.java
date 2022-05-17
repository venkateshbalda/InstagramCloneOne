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

import com.venkyb.instagramone.RepoInterface.RepoInterface;
import com.venkyb.instagramone.RepoInterface.postsInterface;
import com.venkyb.instagramone.PojoClass.*;
import com.venkyb.instagramone.ServiceClass.*;
@RestController
public class ControllerClass {
	
	@Autowired
	RepoInterface repo;
	
	@Autowired
	postsInterface posts;
	
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
			return("user added");
	}
		
	}
		
	
	String currentUsername="venkybalda";
	@PostMapping("/addpost")
	public posts addPost(posts post) {
		int uid = repo.getByUName(currentUsername).get(0).getId();
		//LocalDateTime now = LocalDateTime.now();
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		String imageurl = "dummy post image path, will use it in the future probably";
		post.setTimestamp(timestamp);
		post.setImageurl(imageurl);
		post.setUserid(uid);
		post.setUsername(currentUsername);
		posts.save(post);
		return post;
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
		
	@PutMapping("/updatepost")
	public String updatePost(posts post) {
		int uid = repo.getByUName(currentUsername).get(0).getId();
		//LocalDateTime now = LocalDateTime.now();
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		String imageurl = "dummy post image path, will use it in the future probably";
		post.setTimestamp(timestamp);
		post.setImageurl(imageurl);
		post.setUserid(uid);
		post.setUsername(currentUsername);
		posts.save(post);
		return("post updated");
	}
	
	@GetMapping("/posts")
	public List<posts> getPosts(){
		return posts.findAll();
	}
	
	@GetMapping("/posts/{uname}")
	public List<posts> getPostByUser(@PathVariable("uname") String uname){
		int uid = getUser(uname).get(0).getId();
		return posts.getByUserid(uid);
	}
	
	@GetMapping("/myposts")
	public List<posts> getMyPosts(){
		int uid = repo.getByUName(currentUsername).get(0).getId();
		return posts.getByUserid(uid);
	}
	
	@DeleteMapping("/postid/{pid}")
	public String deletePost(@PathVariable("pid") int pid) {
		posts.deleteById(pid);
		return("post deleted");	
	}
	
}
