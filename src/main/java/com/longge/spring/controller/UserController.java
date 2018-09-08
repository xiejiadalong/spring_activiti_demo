package com.longge.spring.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longge.spring.entity.Dictionary;
import com.longge.spring.service.UserService;
import com.longge.spring.util.DateUtil;
import com.longge.spring.util.UuidUtils;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	Logger log=Logger.getLogger(UserController.class);
	
	@RequestMapping(value="/getUsers",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getUsers(@RequestBody Map<String, Object> pageParms,HttpServletRequest request)
	{
		return userService.getUsers(pageParms);
	}

	
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> addUser(@RequestBody Map<String, String> user,HttpServletRequest request)
	{

	   user.put("user_id", UuidUtils.getUUID32());
	   user.put("status", "1");
	   user.put("create_date", DateUtil.ymdFormat.format(new Date()));
	   userService.addUser(user);
	   return null;
	}
	
	
	@RequestMapping(value="/deleteUser",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> deleteUser(@RequestBody Map<String, String> user,HttpServletRequest request)
	{
	   userService.deleteUser(user);
	   return null;
	}
}
