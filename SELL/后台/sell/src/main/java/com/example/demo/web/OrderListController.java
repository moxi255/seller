package com.example.demo.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.OrderList;
import com.example.demo.service.OrderListService;

@RestController
public class OrderListController {
	@Autowired OrderListService orderListService;
	
	@GetMapping ("/orderlists/{id}")
	public  OrderList get(@PathVariable("id") int id) throws Exception{
		OrderList bean=orderListService.get(id);
		return bean;
	}
	@PostMapping ("/orderlists")
	public Object add(@RequestBody OrderList bean) throws Exception{
		orderListService.add(bean);
		return bean;
	}
	@DeleteMapping("/orderlists/{id}")
	public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
		orderListService.delete(id);
        return null;
    }
	@PutMapping("/orderlists")
    public Object update(@RequestBody OrderList bean) throws Exception {
		orderListService.update(bean);
        return bean;
    }

}
