package com.longge.spring.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.longge.spring.entity.FilePathEntity;

@Mapper
public interface UserDao {

	/**
	 * 获取用户信息
	 * @param parms
	 * @return
	 */
	public List<Map<String, Object>> getUsers(Map parms);
	public int getUsersTotal(Map parms);
	public List<Map<String, Object>> getUserRoles(Map parms); 
	public void addUser(Map user);
	public void addUserRole(Map userRole);
	public void deleteUser(Map user);
}
