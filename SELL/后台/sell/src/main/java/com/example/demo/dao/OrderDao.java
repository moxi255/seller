package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Order;

public interface OrderDao extends JpaRepository<Order,Integer> {
	List<Order> findByStatus(String status);

}
