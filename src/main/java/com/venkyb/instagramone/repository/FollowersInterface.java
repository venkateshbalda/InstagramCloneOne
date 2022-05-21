package com.venkyb.instagramone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.venkyb.instagramone.models.*;

public interface FollowersInterface extends JpaRepository<FollowersClass, Integer>{
	@Query("FROM FollowersClass WHERE userid=?1 ORDER BY userid")
	List<FollowersClass> getByUserid(int userid);
}
