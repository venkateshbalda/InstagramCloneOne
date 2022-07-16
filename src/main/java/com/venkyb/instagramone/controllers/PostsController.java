package com.venkyb.instagramone.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.venkyb.instagramone.ServiceClass.*;
import com.venkyb.instagramone.controllers.*;
import com.venkyb.instagramone.models.*;
import com.venkyb.instagramone.repository.FollowersInterface;
import com.venkyb.instagramone.repository.RepoInterface;
import com.venkyb.instagramone.repository.postsInterface;


@RestController
public class PostsController {
	
	@Autowired
	RepoInterface repo;
	
	@Autowired
	postsInterface posts;
	
	@Autowired
	FollowersInterface follow;
	
	String currentUsername=new ServiceClass().getCurrentLoggedInUser();
	

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
		post.setLikes(0);
		post.setLikedby("#");
		posts.save(post);
		return post;
	}
	
	@PutMapping("/updatepost")
	public String updatePost(posts post) {
		int pid = post.getPostid();
		//LocalDateTime now = LocalDateTime.now();
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		post.setTimestamp(timestamp);
		post.setImageurl(posts.getByPostid(pid).get(0).getImageurl());
		post.setUserid(repo.getByUName(currentUsername).get(0).getId());
		post.setLikes(posts.getByPostid(pid).get(0).getLikes());
		post.setUsername(currentUsername);
		post.setLikedby(posts.getByPostid(pid).get(0).getLikedby());
		posts.save(post);
		return("post updated");
	}
	
	@PutMapping("/postlikeunlike/{pid}")
	public String likeUnlikePost(@PathVariable("pid") Integer pid) {
		posts likes = new posts();

		int uid = repo.getByUName(currentUsername).get(0).getId();
		if(posts.getByPostid(pid).get(0).getLikedby().contains("#"+uid+"#")) {
			int noOflikes = posts.getByPostid(pid).get(0).getLikes()-1;
			buildLikes(pid, likes, noOflikes);
			likes.setLikes(noOflikes);
			likes.setLikedby(posts.getByPostid(pid).get(0).getLikedby().replace("#"+uid+"#", "#"));
			posts.save(likes);
			System.out.println(posts.getByPostid(pid).get(0).getLikes());
			return("unliked");
		}
		else {
			int noOflikes = posts.getByPostid(pid).get(0).getLikes()+1;
			buildLikes(pid, likes, noOflikes);
			likes.setLikes(noOflikes);
			likes.setLikedby(posts.getByPostid(pid).get(0).getLikedby()+uid+"#");
			posts.save(likes);
			System.out.println(posts.getByPostid(pid).get(0).getLikes());
			return("liked");
		}
	}

	private void buildLikes(Integer pid, posts likes, int noOfLikes) {
		likes.setPostid(pid);
		likes.setDescription(posts.getByPostid(pid).get(0).getDescription());
		likes.setImageurl(posts.getByPostid(pid).get(0).getImageurl());
		likes.setTimestamp(posts.getByPostid(pid).get(0).getTimestamp());
		likes.setUserid(posts.getByPostid(pid).get(0).getUserid());
		likes.setUsername(posts.getByPostid(pid).get(0).getUsername());
		
	}
	
	
	@GetMapping("/posts")
	public List<posts> getPosts(){
		return posts.findAll();
	}
	
	@GetMapping("/posts/{uname}")
	public List<posts> getPostByUser(@PathVariable("uname") String uname){
		int uid = repo.getByUName(uname).get(0).getId();
		return posts.getByUserid(uid);
	}
	
	@GetMapping("/postid/{pid}")
	public List<posts> getPostsById(@PathVariable("pid") int pid){
		return posts.getByPostid(pid);
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
