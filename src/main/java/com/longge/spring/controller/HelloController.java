package com.longge.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longge.spring.entity.FilePathEntity;
import com.longge.spring.service.FileService;

@Controller
public class HelloController {

	   @Autowired
	   FileService fileService;
	
		@RequestMapping(value="/hello",method=RequestMethod.GET)
		@ResponseBody
		public Map<Object, Object> getReviews(HttpServletRequest request)
		{
			Map<Object, Object> res =new HashMap<>();
			res.put("msg", "hello");
			return res;
			
		}
		
		@RequestMapping(value="/insertFile",method=RequestMethod.GET)
		@ResponseBody
		public Map<Object, Object> insertFile(HttpServletRequest request)
		{
			FilePathEntity path=new FilePathEntity();
			path.setComment("sdsd");
			path.setId(10);
			path.setPath("54666");
			fileService.insertFile(path);
			Map<Object, Object> res =new HashMap<>();
			res.put("msg", "hello");
			return res;
			
		}
}
