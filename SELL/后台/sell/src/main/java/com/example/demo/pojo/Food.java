package com.example.demo.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

@Entity

@Table(name = "food") 
public class Food {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") 
	private int Id;
	@Column(name = "name")
	private String name;
	@Column(name = "price")
	private Double price;
	@Column(name = "oldPrice",nullable=true)
	private Double oldPrice;
	@Column(name = "description",nullable=true)
	private String description;
	@Column(name = "sellCount")
	private int sellCount;
	@Column(name = "rating")
	private int rating;
	@Column(name = "info")
	private String info;
	@Column(name = "icon")
	private String icon;
	@Column(name = "image")
	private String image;
	private int foodTypeid;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(Double oldPrice) {
		this.oldPrice = oldPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSellCount() {
		return sellCount;
	}
	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getFoodTypeid() {
		return foodTypeid;
	}
	public void setFoodTypeid(int foodTypeid) {
		this.foodTypeid = foodTypeid;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="foodTypeid",nullable=true,insertable=false,updatable=false)
	@JSONField(serialize = false)
	private FoodType foodType;
	public FoodType getFoodType(){
		return foodType;
	}
	public void setFoodType (FoodType foodType) {
		this.foodType=foodType;
	}
	@OneToMany(mappedBy="food",cascade ={CascadeType.ALL},fetch=FetchType.EAGER)
	private List<FoodRatings> ratings;
	public List<FoodRatings>getRatings(){
		return ratings;
	}
	public void setRatings(List<FoodRatings>ratings) {
		this.ratings=ratings;
	}
	
	
}
