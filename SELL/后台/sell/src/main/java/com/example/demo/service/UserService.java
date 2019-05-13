package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demo.dao.UserDao;
import com.example.demo.pojo.User;



@Service
public class UserService {
	@Autowired UserDao userDao;
	
	
	public List<User>list(){
		Sort sort = new Sort(Sort.Direction.DESC,"id");
		return userDao.findAll(sort);
	}
	public void add(User bean) {
		userDao.save(bean);
	}
	public void delete(int id) {
		userDao.deleteById(id);;
	}
	public User get(int id) {
		Optional<User> findById = userDao.findById(id);
		return findById.get();
	}
	public void update(User bean) {
		userDao.save(bean);
	}
	public int login(String username,String password) {
		User user = userDao.findByUsernameAndPassword(username, password);
        return user.getUserId();
	}

}
