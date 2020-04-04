package com.ipnetinstitute.csc394.backend.controller;

import com.ipnetinstitute.csc394.backend.dao.BaseEntityRepository;
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
public class BaseController implements InitializingBean {
	@Autowired
	private BaseEntityRepository<User> userRepo;
	@Autowired
	private BaseEntityRepository<Teacher> teacherRepo;

	@Autowired
	private BaseEntityRepository<Student> studentRepo;

	private static Map<String, BaseEntityRepository> repos;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/get/{entity}/{id}", method = RequestMethod.GET)
	public <T extends BaseEntity> Optional<T> getById(@PathVariable("entity") String entity,
			@PathVariable("id") Integer id) throws ClassNotFoundException {
		Optional<T> result = null;
		try {
			result = (Optional<T>) repos.get(entity).findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	@RequestMapping(value = "/getAll/{entity}", method = RequestMethod.GET)
	public List getAll(@PathVariable("entity") String entity) throws ClassNotFoundException {
		List result = new ArrayList();
		try {
			result = (List) repos.get(entity).findAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	@RequestMapping(value = "/count/{entity}", method = RequestMethod.GET)
	public long count(@PathVariable("entity") String entity) throws ClassNotFoundException {
		long result = 0;
		try {
			result = repos.get(entity).count();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/save/{entity}", method = RequestMethod.POST)
	public <T extends BaseEntity> T save(@PathVariable("entity") String entity, @RequestBody BaseEntity be)
			throws ClassNotFoundException {
		T result = (T) be;
		try {
			if(be.getId()==null) {
				be.setCreateDateTime(new Date());
				be.setModDateTime(new Date());
			}else {
				be.setModDateTime(new Date());
			}
			result = (T) repos.get(entity).save(be);
			
		} catch (Exception e) {
			result.setError(e.getMessage());
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delete/{entity}", method = RequestMethod.POST)
	public String delete(@PathVariable("entity") String entity, @RequestBody BaseEntity be)
			throws ClassNotFoundException {
		try {
			repos.get(entity).delete(be);
			return "Success";
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "delete/{entity}/{id}", method = RequestMethod.GET)
	public String deleteById(@PathVariable("entity") String entity, @PathVariable("id") Integer id)
			throws ClassNotFoundException {
		try {
			repos.get(entity).deleteById(id);
			return "Success";
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		repos = new HashMap<String, BaseEntityRepository>();
		repos.put("user", userRepo);
		repos.put("teacher", teacherRepo);
		repos.put("student", studentRepo);
	}
}
