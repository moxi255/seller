package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.service.FoodService;
import com.example.demo.service.FoodTypeService;

/**
 * @ClassName foodTypeTest
 * @Description TODO
 * @author 61430
 * @Date 2019年4月6日 上午9:33:10
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class foodTypeTest {
    @Autowired FoodTypeService foodTypeService;
    @Autowired FoodService foodService;
   
    @Test
    public void contextLoads1() {
        foodService.delete(106);
        
    }
}
