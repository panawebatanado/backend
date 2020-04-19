package com.ipnetinstitute.csc394.backend.controller;

import com.ipnetinstitute.csc394.backend.dao.BaseEntityRepository;
import com.ipnetinstitute.csc394.backend.dao.UserEntityRepository;
import com.ipnetinstitute.csc394.backend.entity.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value = "/services")
public class UserController {
	@Autowired
	private UserEntityRepository userRepo;

	@RequestMapping(value = "/getByUserName/{userName}", method = RequestMethod.GET)
	public User getByUserName(@PathVariable("userName") String userName) throws ClassNotFoundException {
		User result = null;
		try {
			result = userRepo.findByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User login(@RequestBody  User user) throws ClassNotFoundException {
		User result = null;
		try {
			result = userRepo.login(user.getUserName(), user.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public String deleteUser(@RequestBody  String userName) throws ClassNotFoundException {
		String result = "Success";
		try {
			 userRepo.deleteByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
			result="Error: "+e.getMessage();
		} finally {
			return result;
		}
	}
}
