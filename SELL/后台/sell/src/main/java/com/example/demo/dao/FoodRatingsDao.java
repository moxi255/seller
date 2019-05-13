package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.FoodRatings;

public interface FoodRatingsDao extends JpaRepository<FoodRatings,Integer> {

}
