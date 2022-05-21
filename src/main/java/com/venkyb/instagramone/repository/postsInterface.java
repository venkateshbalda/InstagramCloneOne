package com.venkyb.instagramone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.venkyb.instagramone.models.posts;

public interface postsInterface extends JpaRepository<posts, Integer>{
	@Query("FROM posts WHERE userid=?1 ORDER BY userid")
	List<posts> getByUserid(int userid);
	@Query("FROM posts WHERE postid=?1 ORDER BY postid")
	List<posts> getByPostid(int postid);

}
