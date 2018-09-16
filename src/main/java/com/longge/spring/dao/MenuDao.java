package com.longge.spring.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.longge.spring.entity.TreeNode;
@Mapper
public interface MenuDao {
	public List<Map<String, Object>> get(Map parms);
	
	public List<Map<String, Object>> getAll(Map parms);
	public int getTotal(Map parms);
	public void add(Map parms);
	public void addMenuPermission(Map parms);
	
	public void delete(Map parms);
	public void deleteMenuPermission(Map parms);
	
	public List<TreeNode> getDetail(Map parms);
}
