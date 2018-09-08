package com.longge.spring.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longge.spring.dao.RoleDao;
import com.longge.spring.dao.UserDao;
import com.longge.spring.entity.Dictionary;
import com.longge.spring.util.PageUtil;

@Service
public class RoleService {

	@Autowired
	RoleDao roleDao;
	
	@Autowired
    Dictionary dictionary;

	public List<Map<String, Object>> getRoles(Map<String, Object> parms) {
		//分页参数设置
		PageUtil.handlePageParms(parms);
		List<Map<String, Object>> resData=roleDao.getRoles(parms);
        int totle_record = roleDao.getTotal(parms);
		PageUtil.handlePageResult(resData, totle_record,parms);
		dictionary.transferData(resData, "role_status");
        return resData;
	}

	public void addRole(Map role) {
		roleDao.addRole(role);
	}

	public void deleteRole(Map role) {
		roleDao.deleteRole(role);
	}
}
