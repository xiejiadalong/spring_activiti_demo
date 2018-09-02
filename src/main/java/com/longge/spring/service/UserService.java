package com.longge.spring.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longge.spring.dao.UserDao;
import com.longge.spring.util.PageUtil;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public List<Map<String, Object>> getUsers(Map<String, Object> parms) {

		//分页参数设置
		PageUtil.handlePageParms(parms);
		List<Map<String, Object>> resData=userDao.getUsers(parms);
        int totle_record = userDao.getUsersTotal(parms);
		PageUtil.handlePageResult(resData, totle_record,parms);
        return resData;

	}

	public void addUser(Map user) {
		userDao.addUser(user);
	}

	public void deleteUser(Map user) {
		userDao.deleteUser(user);
	}
}
