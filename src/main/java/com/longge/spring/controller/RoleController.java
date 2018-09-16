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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.longge.spring.service.RoleService;
import com.longge.spring.service.UserService;
import com.longge.spring.util.DateUtil;
import com.longge.spring.util.UuidUtils;

@RestController
public class RoleController {

	@Autowired
	RoleService roleService;
	
	Logger log=Logger.getLogger(this.getClass());
	
	@RequestMapping(value="/getRoles",method=RequestMethod.POST)
	public List<Map<String, Object>> getRoles(@RequestBody Map<String, Object> pageParms,HttpServletRequest request)
	{
		return roleService.getRoles(pageParms);
	}
	
	@RequestMapping(value="/getAllRoles",method=RequestMethod.POST)
	public List<Map<String, Object>> getAllRoles(@RequestBody Map<String, Object> pageParms,HttpServletRequest request)
	{
		return roleService.getAllRoles(pageParms);
	}
	
	@RequestMapping(value="/getRolePermissions",method=RequestMethod.POST)
	public List<Map<String, Object>> getRolePermissions(@RequestBody Map<String, Object> pageParms,HttpServletRequest request)
	{
		return roleService.getRolePermissions(pageParms);
	}
	
	

	@RequestMapping(value="/addRole",method=RequestMethod.POST)
	public List<Map<String, Object>> addRole(@RequestBody Map<String, String> role,HttpServletRequest request)
	{
	   role.put("role_id", UuidUtils.getUUID32());
	   role.put("create_date", DateUtil.ymdFormat.format(new Date()));
	   roleService.addRole(role);
	   return null;
	}
	
	@RequestMapping(value="/addRolePemission",method=RequestMethod.POST)
	public List<Map<String, Object>> addRolePemission(@RequestBody Map<String, String> parms,HttpServletRequest request)
	{
	   roleService.addRolePemission(parms);
	   return null;
	}
	
	@RequestMapping(value="/deleteRole",method=RequestMethod.POST)
	public List<Map<String, Object>> deleteRole(@RequestBody Map<String, String> role,HttpServletRequest request)
	{
		roleService.deleteRole(role);
	   return null;
	}
	
	
	
}
