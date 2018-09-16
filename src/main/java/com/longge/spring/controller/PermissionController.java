package com.longge.spring.controller;

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

import com.longge.spring.service.PermissionService;

@RestController
public class PermissionController {

	@Autowired
	PermissionService permissionService;
	
	Logger log=Logger.getLogger(this.getClass());
	
	@RequestMapping(value="/getPermissions",method=RequestMethod.POST)
	public List<Map<String, Object>> get(@RequestBody Map<String, Object> pageParms,HttpServletRequest request)
	{
		return permissionService.get(pageParms);
	}
	
	@RequestMapping(value="/getAllPermissions",method=RequestMethod.POST)
	public List<Map<String, Object>> getAll(@RequestBody Map<String, Object> pageParms,HttpServletRequest request)
	{
		return permissionService.getAll(pageParms);
	}

	@RequestMapping(value="/addPermission",method=RequestMethod.POST)
	public List<Map<String, Object>> add(@RequestBody Map<String, String> parms,HttpServletRequest request)
	{
	   permissionService.add(parms);
	   return null;
	}
	
	@RequestMapping(value="/deletePermission",method=RequestMethod.POST)
	public List<Map<String, Object>> delete(@RequestBody Map<String, String> parms,HttpServletRequest request)
	{
	   permissionService.delete(parms);
	   return null;
	}
}
