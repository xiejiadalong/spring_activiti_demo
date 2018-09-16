package com.longge.spring.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface RoleDao {
	public List<Map<String, Object>> getRoles(Map parms);
	public List<Map<String, Object>> getAllRoles(Map parms);
	public int getTotal(Map parms);
	public void addRole(Map parms);
	public void addRolePemission(Map parms);
	public void deleteRole(Map role);
	public List<Map<String, Object>> getRolePermissions(Map parms);
	
}
