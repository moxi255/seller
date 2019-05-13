package com.example.demo.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

@Table(name = "foodType") 
public class FoodType {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foodTypeid")
	private int foodTypeId;
	private String foodTypename;
	private int type;
	public int getFoodTypeId() {
		return foodTypeId;
	}
	public void setFoodTypeId(int foodTypeId) {
		this.foodTypeId = foodTypeId;
	}
	public String getFoodTypename() {
		return foodTypename;
	}
	public void setFoodTypename(String foodTypename) {
		this.foodTypename = foodTypename;
	}

	@OneToMany(mappedBy="foodType",cascade= {CascadeType.ALL},fetch=FetchType.EAGER)
	private List<Food> foods;
	public List<Food>getFoods(){
		return foods;
	}
	public void setFoods(List<Food>foods) {
		this.foods=foods;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "FoodType [foodTypeId=" + foodTypeId + ", foodTypename=" + foodTypename + ", type=" + type + ", foods="
				+ foods + "]";
	}

}
