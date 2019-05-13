package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderDao;
import com.example.demo.pojo.Order;

@Service
public class OrderService {
	@Autowired OrderDao orderDao;
	
	
	public List<Order>list(){
		return orderDao.findAll();
	}
	public void add(Order bean) {
		orderDao.save(bean);
	}
	public void delete(int id) {
		orderDao.deleteById(id);;
	}
	public Order get(int id) {
		Optional<Order> findById = orderDao.findById(id);
		return findById.get();
	}
	public List<Order> get(String status) {
		System.out.println(status);
		return orderDao.findByStatus(status);
	}
	public void update(Order bean) {
		orderDao.save(bean);
	}

}
