package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.FoodDao;
import com.example.demo.pojo.Food;
@Service
public class FoodService {
	@Autowired FoodDao foodDao;
	
	public List<Food>list(){
		return foodDao.findAll();
	}
	public Food add(Food bean) {	
		return foodDao.save(bean);
	}
	public void delete(int id) {
		foodDao.deleteById(id);
	}
	public Food get(int id) {
		Optional<Food> findById = foodDao.findById(id);
		return findById.get();
	}
	public void update(Food bean) {
		foodDao.save(bean);
	}


}
