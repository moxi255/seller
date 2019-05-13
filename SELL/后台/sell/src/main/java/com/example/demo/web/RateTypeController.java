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
import com.example.demo.pojo.RateType;
import com.example.demo.service.RateTypeService;

@RestController
public class RateTypeController {
	@Autowired RateTypeService rateTypeService;
	
	@GetMapping ("/rateTypees/{id}")
	public  RateType get(@PathVariable("id") int id) throws Exception{
		RateType bean=rateTypeService.get(id);
		return bean;
	}
	/*@PostMapping ("/rateTypees")
	public Object add(@RequestBody RateType bean) throws Exception{
		rateTypeService.add(bean);
		return bean;
	}*/
	@DeleteMapping("/rateTypees/{id}")
	public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
		rateTypeService.delete(id);
        return null;
    }
	@PutMapping("/rateTypees")
    public Object update(@RequestBody RateType bean) throws Exception {
		rateTypeService.update(bean);
        return bean;
    }
	@RequestMapping(value="/rateTypees",method=RequestMethod.GET)
	public String getAll() {
		System.out.println("aaaa");
		List<RateType> selectAll=rateTypeService.findAll();
		return JSON.toJSONString(selectAll);
	}
	@RequestMapping(value="/rateTypenames",method=RequestMethod.GET)
	public String getselectAllRateTypeName() {
		List<RateType> selectAllRateTypeName=rateTypeService.findAll();
		PropertyFilter propertyFilter=new PropertyFilter() {

			@Override
			public boolean apply(Object object, String name, Object value) {
				// TODO Auto-generated method stub
				if(name.equalsIgnoreCase("ratings")||name.equalsIgnoreCase("type")) {
					return false;
				}
				return true;
			}
			
		};
		
		return JSON.toJSONString(selectAllRateTypeName,propertyFilter);
	}

}
