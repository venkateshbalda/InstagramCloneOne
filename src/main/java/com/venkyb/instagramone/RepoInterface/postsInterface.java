package com.venkyb.instagramone.RepoInterface;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.venkyb.instagramone.PojoClass.posts;

public interface postsInterface extends JpaRepository<posts, Integer>{
	@Query("FROM posts WHERE userid=?1 ORDER BY userid")
	List<posts> getByUserid(int userid);

}
