package com.longge.spring.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface RoleDao {
	public List<Map<String, Object>> getRoles(Map parms);
	public int getTotal(Map parms);
	public void addRole(Map role);
	public void deleteRole(Map role);
}
