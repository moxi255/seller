package com.example.demo.pojo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

@Entity

@Table(name = "foodRatings") 
public class FoodRatings {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int Id;
	private String username;
	@JSONField(format="yyyy-MM-dd")
	private Date rateTime;
	@Column(name = "rateType")
	private int rateType;
	private String text;
	private String avatar;
	private double score;
	private String recommend;
	private int foodId;
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	



	public int getRateType() {
		return rateType;
	}
	public void setRateType(int rateType) {
		this.rateType = rateType;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Date getRateTime() {
		return rateTime;
	}
	public void setRateTime(Date rateTime) {
		this.rateTime = rateTime;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="foodid")
	@JSONField(serialize = false)
	private Food food;
	public Food getFood(){
		return food;
	}
	public void setFood (Food food) {
		this.food=food;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	
}
