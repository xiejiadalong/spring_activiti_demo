package com.longge.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.longge.spring.dao.MenuDao;
import com.longge.spring.entity.Dictionary;
import com.longge.spring.entity.TreeNode;
import com.longge.spring.util.UuidUtils;

@Service
public class MenuService {

	@Autowired
	MenuDao menuDao;
	
	@Autowired
    Dictionary dictionary;

	public List<Map<String, Object>> get(Map<String, Object> parms) {
		//分页参数设置
		List<Map<String, Object>> resData=menuDao.get(parms);
        return resData;
	}
	
	public List<Map<String, Object>> getAll(Map<String, Object> parms) {
		//分页参数设置
		List<Map<String, Object>> resData=menuDao.getAll(parms);
        return resData;
	}

	public void add(Map parms) {
		parms.put("menu_id", UuidUtils.getUUID32());
		menuDao.add(parms);
		menuDao.addMenuPermission(parms);
	}

	public void delete(Map parms) {
		menuDao.deleteMenuPermission(parms);
		menuDao.delete(parms);
	}
	
	/**
	 * 构建三层菜单树数据
	 */
	public List<TreeNode> createTreeMenu(Map parms){
		List<TreeNode> rootMenu=menuDao.getDetail(parms);
	    List<TreeNode> menuList = new ArrayList<TreeNode>();
	    // 先找到所有的一级菜单
	    for (int i = 0; i < rootMenu.size(); i++) {
	        if ("1".equals(rootMenu.get(i).getLevel())) {
	            menuList.add(rootMenu.get(i));
	        }
	    }
		 for (TreeNode menu : menuList) {
		        menu.setNodes(getChild(menu.getMenu_id(), rootMenu));
		    }
		return menuList;
	}
	
	
	/**
	 * 递归查找子菜单
	 * 
	 * @param id
	 *            当前菜单id
	 * @param rootMenu
	 *            要查找的列表
	 * @return
	 */
	public List<TreeNode> getChild(String id, List<TreeNode> rootMenu) {
	    // 子菜单
	    List<TreeNode> childList = new ArrayList<>();
	    for (TreeNode node : rootMenu) {
	        // 遍历所有节点，将父菜单id与传过来的id比较
	        if (!StringUtils.isEmpty(node.getP_id())) {
	            if (node.getP_id().equals(id)) {
	                childList.add(node);
	            }
	        }
	    }
	    if (childList.size() == 0) {
	        return null;
	    }
	    // 把子菜单的子菜单再循环一遍
	    for (TreeNode node : childList) {// 没有url子菜单还有子菜单
	    	   node.setNodes(getChild(node.getMenu_id(), rootMenu));
	    } 
	    return childList;
	}
	
}
