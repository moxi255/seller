package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dao.FoodRatingsDao;
import com.example.demo.pojo.FoodRatings;
@Service
public class FoodRatingsService {
	@Autowired FoodRatingsDao foodRatingsDao;
	
	public List<FoodRatings>list(){
		return foodRatingsDao.findAll();
	}
	public void add(FoodRatings bean) {
		foodRatingsDao.save(bean);
	}
	public void delete(int id) {
		foodRatingsDao.deleteById(id);;
	}
	public FoodRatings get(int id) {
		Optional<FoodRatings> findById = foodRatingsDao.findById(id);
		return findById.get();
	}
	public void update(FoodRatings bean) {
		foodRatingsDao.save(bean);
	}
	

}
