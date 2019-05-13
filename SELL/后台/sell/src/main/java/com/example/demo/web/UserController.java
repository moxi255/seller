package com.example.demo.web;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;


@RequestMapping()
@RestController
public class UserController {
	@Autowired UserService userService;
	
	@GetMapping ("/useres/{id}")
	public  User get(@PathVariable("id") int id) throws Exception{
		User bean=userService.get(id);
		return bean;
	}
	@PostMapping (value = "/useres/add",consumes = "application/json")
	public Object add(@RequestBody User bean) throws Exception{
		userService.add(bean);
		return bean;
	}
	@DeleteMapping("/useres/{id}")
	public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
		userService.delete(id);
        return null;
    }
	@PutMapping("/useres")
    public Object update(@RequestBody User bean) throws Exception {
		userService.update(bean);
        return bean;
    }
	
	@PostMapping ("/useres/login")
	public int login(HttpServletRequest request) throws Exception{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
	    int a=	userService.login(username, password);
	    System.out.println(a);
		return a;
	}

}
