package com.longge.spring.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface PermissionDao {
	public List<Map<String, Object>> get(Map parms);
	public List<Map<String, Object>> getAll(Map parms);
	public int getTotal(Map parms);
	public void add(Map parms);
	public void delete(Map parms);
}
