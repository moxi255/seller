package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.RateType;

public interface RateTypeDao extends JpaRepository<RateType,Integer> {

}
