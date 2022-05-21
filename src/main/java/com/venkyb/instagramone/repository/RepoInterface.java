package com.venkyb.instagramone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.venkyb.instagramone.models.*;

public interface RepoInterface extends JpaRepository<PojoClass, Integer> {
	
	@Query("FROM PojoClass WHERE Username=?1 ORDER BY Username")
	List<PojoClass> getByUName(String UName);
	
	List<PojoClass> deleteByUsername(String userName);
}
