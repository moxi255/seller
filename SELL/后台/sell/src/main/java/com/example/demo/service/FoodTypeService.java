package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dao.FoodTypeDao;
import com.example.demo.pojo.FoodType;
@Service
public class FoodTypeService {
	@Autowired FoodTypeDao foodTypeDao;
	
	public List<FoodType>list(){
		Sort sort = new Sort(Sort.Direction.DESC,"id");
		return foodTypeDao.findAll(sort);
	}
	public void add(FoodType bean) {
		foodTypeDao.save(bean);
	}
	public void delete(int id) {
		foodTypeDao.deleteById(id);;
	}
	public FoodType get(int id) {
		Optional<FoodType> findById = foodTypeDao.findById(id);
		return findById.get();
	}
	public void update(FoodType bean) {
		foodTypeDao.save(bean);
	}
	public List<FoodType>findAll(){
		List<FoodType> foodType=foodTypeDao.findAll();
		return foodType;
	}

}
