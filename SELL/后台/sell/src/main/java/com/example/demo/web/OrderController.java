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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.Order;
import com.example.demo.service.OrderService;

@RestController
public class OrderController {
	@Autowired OrderService orderService;
	
	
	@GetMapping ("/orderes/{id}")
	public  Order get(@PathVariable("id") int id) throws Exception{
		Order bean=orderService.get(id);
		return bean;
	}
	@PostMapping("/orderes1")
	public  String  get(@RequestParam("status") String status) throws Exception{
		//public  Order get(HttpServletRequest request)throws Exception{

		System.out.println("status="+status);
		List<Order> bean=orderService.get(status);
		return JSON.toJSONString(bean);
	}
	@PostMapping ("/orderes")
	public String add(@RequestBody Order bean) throws Exception{
		orderService.add(bean);
		return JSON.toJSONString(bean);
	}
	@DeleteMapping("/orderes/{id}")
	public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
		orderService.delete(id);
        return null;
    }
	@PutMapping("/orderes")
    public Object update(@RequestBody Order bean) throws Exception {
		Order order=orderService.get(bean.getId());
		order.setStatus(bean.getStatus());
		orderService.update(order);
        return order;
    }
	@RequestMapping(value="/orderes",method=RequestMethod.GET)
	public String getAll() {
		List<Order> selectAll=orderService.list();
		System.out.println("orderes_selectAll="+selectAll);
		
		return JSON.toJSONString(selectAll);
	}
	
	/*@RequestMapping(value="/orderes",method=RequestMethod.GET)
	public String getAll(HttpServletRequest request) {
		List<Order> selectAll=orderService.list();
		if(request.getParameter("status")!=null&&request.getParameter("status")!=""&&request.getParameter("status").length()>0){
		String status=request.getParameter("status");
		System.out.println(status);
		List <Order> DelselectList=new ArrayList<>();
		for (Order or : selectAll) {
		    if (or.getStatus().equals(status)) {
		        DelselectList.add(or);
		    }
		}
		selectAll.removeAll(DelselectList);
			}
		return JSON.toJSONString(selectAll);
	}	*/

	
}
