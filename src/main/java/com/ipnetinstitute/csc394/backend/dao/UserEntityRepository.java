package com.ipnetinstitute.csc394.backend.dao;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ipnetinstitute.csc394.backend.entity.User;

//@NoRepositoryBean
@Transactional
public interface UserEntityRepository extends BaseEntityRepository<User> {

	  @Query("select u from User u where u.userName = ?1")
	  User findByUserName(String userName);
	  
	  @Query("select u from User u where u.userName = ?1 and password=?2")
	  User login(String userName, String password);
	  
	  @Modifying
	  @Query("delete from User u where u.userName = ?1")
	  void deleteByUserName(String userName);
}
