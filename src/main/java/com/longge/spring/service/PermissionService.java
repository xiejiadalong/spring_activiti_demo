package com.longge.spring.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longge.spring.dao.PermissionDao;
import com.longge.spring.dao.RoleDao;
import com.longge.spring.dao.UserDao;
import com.longge.spring.entity.Dictionary;
import com.longge.spring.util.PageUtil;
import com.longge.spring.util.UuidUtils;

@Service
public class PermissionService {

	@Autowired
	PermissionDao permissionDao;
	
	@Autowired
    Dictionary dictionary;

	public List<Map<String, Object>> get(Map<String, Object> parms) {
		//分页参数设置
		PageUtil.handlePageParms(parms);
		List<Map<String, Object>> resData=permissionDao.get(parms);
        int totle_record = permissionDao.getTotal(parms);
		PageUtil.handlePageResult(resData, totle_record,parms);
//		dictionary.transferData(resData, "role_status");
        return resData;
	}
	
	public List<Map<String, Object>> getAll(Map<String, Object> parms) {
		//分页参数设置
		List<Map<String, Object>> resData=permissionDao.getAll(parms);
//		dictionary.transferData(resData, "role_status");
        return resData;
	}

	public void add(Map parms) {
		parms.put("permission_id", UuidUtils.getUUID32());
		permissionDao.add(parms);
	}

	public void delete(Map parms) {
		permissionDao.delete(parms);
	}
}
