package com.longge.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.longge.spring.entity.TreeNode;
import com.longge.spring.service.MenuService;

@RestController
public class MenuController {

	@Autowired
	MenuService menuService;
	
	Logger log=Logger.getLogger(this.getClass());
	
	@RequestMapping(value="/getMenus",method=RequestMethod.POST)
	public List<Map<String, Object>> get(@RequestBody Map<String, Object> pageParms,HttpServletRequest request)
	{
		return menuService.get(pageParms);
	}
	
	@RequestMapping(value="/getAllMenus",method=RequestMethod.POST)
	public List<Map<String, Object>> getAll(@RequestBody Map<String, Object> pageParms,HttpServletRequest request)
	{
		return menuService.getAll(pageParms);
	}

	@RequestMapping(value="/addMenu",method=RequestMethod.POST)
	public List<Map<String, Object>> add(@RequestBody Map<String, String> parms,HttpServletRequest request)
	{
	   menuService.add(parms);
	   return null;
	}
	
	@RequestMapping(value="/deleteMenu",method=RequestMethod.POST)
	public List<Map<String, Object>> delete(@RequestBody Map<String, String> parms,HttpServletRequest request)
	{
	   menuService.delete(parms);
	   return null;
	}
	
	@RequestMapping(value="/createTreeMenu",method=RequestMethod.POST)
	public List<TreeNode> createTreeMenu(@RequestBody Map<String, Object> pageParms,HttpServletRequest request)
	{
		
		return menuService.createTreeMenu(pageParms);
	}
}
