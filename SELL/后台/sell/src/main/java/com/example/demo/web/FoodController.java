package com.example.demo.web;



import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.Food;
import com.example.demo.service.FoodService;

@RestController
public class FoodController {
	@Autowired FoodService foodService;
	
	@GetMapping ("/foods/{id}")
	public String get(@PathVariable("id") int id) throws Exception{
		 Food bean=foodService.get(id);
		return JSON.toJSONString(bean);
	}
	@PostMapping ("/foods")
	public int add(@RequestBody Food bean) throws Exception{
		foodService.add(bean);
		return bean.getId();
	}
	@DeleteMapping("/foods/{id}")
	public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
		foodService.delete(id);
        return null;
    }
	@PutMapping("/foods")
    public int update(@RequestBody  Food bean) throws Exception {
		foodService.update(bean);
        return bean.getId();
    }
	@RequestMapping(value="/foods",method=RequestMethod.GET)
	public String getAll() {
		List<Food> selectAll=foodService.list();
		return JSON.toJSONString(selectAll);
	}
	/**
     * 文件上传
     * @param picture
     * @param request
     * @return
	 * @throws JSONException 
     */
    @RequestMapping("/upload/image")
    public String uploadImage(@RequestParam("picture") MultipartFile picture, HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        //获取文件在服务器的储存位置
        String path = request.getSession().getServletContext().getRealPath("/upload_image");
        String Url=request.getRequestURL().toString();
        System.out.println(Url);
        String url_temp=Url.substring(0, Url.lastIndexOf("/"));
        System.out.println(url_temp);
        String url=url_temp.substring(0, url_temp.lastIndexOf("/")+1);
        System.out.println(url);
        File filePath = new File(path);
        System.out.println("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }

        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        String uuid=java.util.UUID.randomUUID().toString();
        String fileName = uuid + name + "." + type;
        System.out.println("新文件名称：" + fileName);

        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);

        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            System.out.println("上传成功");
            //将文件在服务器的存储路径返回
            jsonObject.put("src", url+"upload_image/" + fileName);
            return jsonObject.toString();
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            jsonObject.put("error", "error");
            return jsonObject.toString();
        }
    }
    /**
     * 文件上传
     * @param picture
     * @param request
     * @return
     * @throws JSONException 
     */
    @RequestMapping("/upload/icon")
    public String uploadIcon(@RequestParam("picture") MultipartFile picture, HttpServletRequest request)  {
        JSONObject jsonObject=new JSONObject();
        //获取文件在服务器的储存位置
        String path = request.getSession().getServletContext().getRealPath("/upload_icon");
        String Url=request.getRequestURL().toString();
        System.out.println(Url);
        String url_temp=Url.substring(0, Url.lastIndexOf("/"));
        System.out.println(url_temp);
        String url=url_temp.substring(0, url_temp.lastIndexOf("/")+1);
        System.out.println(url);
        File filePath = new File(path);
        System.out.println("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }

        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        String uuid=java.util.UUID.randomUUID().toString();
        String fileName = uuid + name + "." + type;
        System.out.println("新文件名称：" + fileName);

        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);

        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            System.out.println("上传成功");
            //将文件在服务器的存储路径返回
            jsonObject.put("src", url+"upload_icon/" + fileName);
            return jsonObject.toString();
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            jsonObject.put("error", "error");
            return jsonObject.toString();
        }
    }

}
