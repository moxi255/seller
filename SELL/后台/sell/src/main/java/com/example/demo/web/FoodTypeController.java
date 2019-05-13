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
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.example.demo.pojo.FoodType;
import com.example.demo.service.FoodTypeService;


@RestController
public class FoodTypeController {
	@Autowired FoodTypeService foodTypeService;
	
	
	@GetMapping ("/foodTypes/{id}")
	public  FoodType get(@PathVariable("id") int id) throws Exception{
		FoodType bean=foodTypeService.get(id);
		return bean;
	}
	@PostMapping ("/foodTypes")
	public Object add(@RequestBody FoodType bean) throws Exception{
		foodTypeService.add(bean);
		return bean;
	}
	@DeleteMapping("/foodTypes/{id}")
	public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
		foodTypeService.delete(id);
        return null;
    }
	@PutMapping("/foodTypes")
    public Object update(@RequestBody FoodType bean) throws Exception {
		foodTypeService.update(bean);
        return bean;
    }
	@RequestMapping(value="/foodTypes",method=RequestMethod.GET)
	public String getAll() {
		System.out.println("get方法_foodTypes");
		List<FoodType> selectAll=foodTypeService.findAll();
		for(FoodType food:selectAll) {
			System.out.println("food="+food);
		}
//		System.out.println("食品类别="+selectAll.toString());
		return JSON.toJSONString(selectAll);
	}
	@RequestMapping(value="/foodTypenames",method=RequestMethod.GET)
	public String getselectAllFoodTypeName() {
		List<FoodType> selectAllFoodTypeName=foodTypeService.findAll();
		PropertyFilter propertyFilter=new PropertyFilter() {

			@Override
			public boolean apply(Object object, String name, Object value) {
				// TODO Auto-generated method stub
				if(name.equalsIgnoreCase("foods")||name.equalsIgnoreCase("type")) {
					return false;
				}
				return true;
			}
			
		};
		
		return JSON.toJSONString(selectAllFoodTypeName,propertyFilter);
	}

}
