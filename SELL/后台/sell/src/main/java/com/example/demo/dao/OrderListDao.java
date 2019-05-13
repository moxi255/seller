package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.OrderList;

public interface OrderListDao extends JpaRepository<OrderList,Integer> {
}
