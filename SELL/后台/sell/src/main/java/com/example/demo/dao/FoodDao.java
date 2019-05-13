package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Food;

public interface FoodDao extends JpaRepository<Food,Integer>{

}
