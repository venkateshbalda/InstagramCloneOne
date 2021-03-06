package com.venkyb.instagramone.ControllerClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.venkyb.instagramone.PojoClass.FollowersClass;
import com.venkyb.instagramone.PojoClass.PojoClass;
import com.venkyb.instagramone.RepoInterface.FollowersInterface;
import com.venkyb.instagramone.RepoInterface.RepoInterface;
import com.venkyb.instagramone.RepoInterface.postsInterface;
import com.venkyb.instagramone.ServiceClass.ServiceClass;

@RestController
public class FollowersController {
	
	@Autowired
	RepoInterface repo;
	
	@Autowired
	FollowersInterface followrepo;
	

	String currentUsername=new ServiceClass().getCurrentLoggedInUser();
	
	@PutMapping("/follow/{uname}")
	public String followUser(@PathVariable("uname") String uname) {
		FollowersClass user=new FollowersClass();
		int uid = repo.getByUName(uname).get(0).getId();
		int myid = repo.getByUName(currentUsername).get(0).getId();
		if(uid!=myid) {
			int ufid = followrepo.getByUserid(uid).get(0).getFid();
			System.out.println(uid);
			int myfid = followrepo.getByUserid(myid).get(0).getFid();
			System.out.println(myid);
			String usersFollowingMe = followrepo.getByUserid(myid).get(0).getFollowingme();
			System.out.println(usersFollowingMe);
			String usersIAmFollowing = followrepo.getByUserid(myid).get(0).getIamfollowing();
			System.out.println(usersIAmFollowing.contains("#"+uid+"#"));
			if( !usersIAmFollowing.contains("#"+uid+"#")) {
			System.out.println(usersIAmFollowing);
			user.setFid(myfid);
			user.setUserid(myid);
			user.setFollowingme(usersFollowingMe);
			user.setIamfollowing(usersIAmFollowing+uid+"#");
			followrepo.save(user);
			
			user.setFid(ufid);
			usersFollowingMe = followrepo.getByUserid(uid).get(0).getFollowingme();
			usersIAmFollowing = followrepo.getByUserid(uid).get(0).getIamfollowing();
			user.setUserid(uid);
			user.setFollowingme(usersFollowingMe+myid+"#");
			user.setIamfollowing(usersIAmFollowing);
			followrepo.save(user);
			return("followed");
			}
			else {
				return("already following");
			}
		}
		else {
			return("you can't follow you, basic sense dude");
		}
	}
	
	@PutMapping("/unfollow/{uname}")
	public String unfollowUser(@PathVariable("uname") String uname) {

		FollowersClass user=new FollowersClass();
		int uid = repo.getByUName(uname).get(0).getId();
		int myid = repo.getByUName(currentUsername).get(0).getId();
		if(uid!=myid) {
			int ufid = followrepo.getByUserid(uid).get(0).getFid();
			int myfid = followrepo.getByUserid(myid).get(0).getFid();
			String usersFollowingMe = followrepo.getByUserid(myid).get(0).getFollowingme();
			String usersIAmFollowing = followrepo.getByUserid(myid).get(0).getIamfollowing();
			System.out.println(usersIAmFollowing.contains("#"+uid+"#"));
			if( usersIAmFollowing.contains("#"+uid+"#")) {
				user.setFid(myfid);
				user.setUserid(myid);
				user.setFollowingme(usersFollowingMe);
				usersIAmFollowing=usersIAmFollowing.replace("#"+uid+"#", "#");
				System.out.println(uid);
				user.setIamfollowing(usersIAmFollowing);
				followrepo.save(user);
				
				user.setFid(ufid);
				usersFollowingMe = followrepo.getByUserid(uid).get(0).getFollowingme();
				usersIAmFollowing = followrepo.getByUserid(uid).get(0).getIamfollowing();
				user.setUserid(uid);
				usersFollowingMe=usersFollowingMe.replace("#"+myid+"#", "#");
				System.out.println(myid);
				user.setFollowingme(usersFollowingMe);
				user.setIamfollowing(usersIAmFollowing);
				followrepo.save(user);
				
				return("unfollow successful");
			}
			else {
				return("you have to follow first to unfollow");
			}
		}
		return("you can't unfollow you, basic sense dude");
	}
	
	@GetMapping("/followers")
	public List<String> getMyFollowers(){	
		int myid = repo.getByUName(currentUsername).get(0).getId();
		System.out.println(followrepo.getByUserid(myid).get(0).getFollowingme());
		List<String> followersString = new ArrayList<String>(Arrays.asList(followrepo.getByUserid(myid).get(0).getFollowingme().split("#")));
		followersString.remove("");
		System.out.println(followersString);
		List<String> followersList = new ArrayList<String>();
		for(String s : followersString) followersList.add(repo.getById(Integer.valueOf(s)).getUsername());
		System.out.println(followersList);
		return followersList;
		
	}
	
	@GetMapping("/following")
	public List<String> getMyFollowing(){	
		int myid = repo.getByUName(currentUsername).get(0).getId();
		System.out.println(followrepo.getByUserid(myid).get(0).getIamfollowing());
		List<String> followersString = new ArrayList<String>(Arrays.asList(followrepo.getByUserid(myid).get(0).getIamfollowing().split("#")));
		followersString.remove("");
		System.out.println(followersString);
		List<String> followersList = new ArrayList<String>();
		for(String s : followersString) followersList.add(repo.getById(Integer.valueOf(s)).getUsername());
		System.out.println(followersList);
		return followersList;
		
	}
}