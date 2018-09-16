package com.longge.spring.entity;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	
	private String text;
	private List<TreeNode> nodes=new ArrayList<>();
	
	private String menu_id; 
	private String p_id; 
	private String menu_name; 
	private String menu_type;
	private String status; 
	private String level;
	
	private String url;
	private String id;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<TreeNode> getNodes() {
		return nodes;
	}
	public void setNodes(List<TreeNode> nodes) {
		this.nodes = nodes;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "TreeNode [text=" + text + ", nodes=" + nodes + ", menu_id=" + menu_id + ", p_id=" + p_id
				+ ", menu_name=" + menu_name + ", menu_type=" + menu_type + ", status=" + status + ", level=" + level
				+ "]";
	} 
	
	
	
	

}
