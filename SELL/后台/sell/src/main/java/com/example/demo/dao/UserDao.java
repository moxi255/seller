package com.example.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.User;

public interface UserDao extends JpaRepository<User,Integer> {	
	User findByUsernameAndPassword(String username,String password);
}



