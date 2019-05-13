package com.example.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.FoodType;

public interface FoodTypeDao extends JpaRepository<FoodType,Integer> {
}
