package com.example.demo.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.Food;
import com.example.demo.pojo.FoodRatings;
import com.example.demo.service.FoodRatingsService;

@RestController
public class FoodRatingsController {
	@Autowired FoodRatingsService foodRatingsService;
	
	
	@GetMapping ("/foodRatingses/{id}")
	public  FoodRatings get(@PathVariable("id") int id) throws Exception{
		FoodRatings bean=foodRatingsService.get(id);
		return bean;
	}
	@PostMapping ("/foodRatingses")
	public Object add(@RequestBody FoodRatings bean) throws Exception{
		foodRatingsService.add(bean);
		return bean;
	}
	@DeleteMapping("/foodRatingses/{id}")
	public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
		foodRatingsService.delete(id);
        return null;
    }
	@PutMapping("/foodRatingses")
    public Object update(@RequestBody FoodRatings bean) throws Exception {
		foodRatingsService.update(bean);
        return bean;
    }
	@RequestMapping(value="/foodRatingses",method=RequestMethod.GET)
	public String getAll() {
		List<FoodRatings> selectAll= foodRatingsService.list();
		return JSON.toJSONString(selectAll);
	}

}
