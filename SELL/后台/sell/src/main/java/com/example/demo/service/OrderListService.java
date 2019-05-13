package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderListDao;
import com.example.demo.pojo.OrderList;
@Service
public class OrderListService {
	@Autowired OrderListDao orderListDao;
		
	public List<OrderList> list(){
		Sort sort = new Sort(Sort.Direction.DESC,"id");
		return orderListDao.findAll(sort);
	}
	public OrderList add(OrderList bean) {	
		return orderListDao.save(bean);
	}
	public void delete(int id) {
		orderListDao.deleteById(id);
	}
	public OrderList get(int id) {
		Optional<OrderList> findById = orderListDao.findById(id);
		return findById.get();
	}
	public void update(OrderList bean) {
		orderListDao.save(bean);
	}
}
