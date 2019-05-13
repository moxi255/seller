package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RateTypeDao;
import com.example.demo.pojo.RateType;
@Service
public class RateTypeService {
	@Autowired RateTypeDao rateTypeDao;
	
	public List<RateType>list(){
		Sort sort = new Sort(Sort.Direction.DESC,"id");
		return rateTypeDao.findAll(sort);
	}
	public void add(RateType bean) {
		rateTypeDao.save(bean);
	}
	public void delete(int id) {
		rateTypeDao.deleteById(id);;
	}
	public RateType get(int id) {
		Optional<RateType> findById = rateTypeDao.findById(id);
		return findById.get();
	}
	public void update(RateType bean) {
		rateTypeDao.save(bean);
	}
	public List<RateType>findAll(){
		List<RateType> rateType=rateTypeDao.findAll();
		return rateType;
	}


}
