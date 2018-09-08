package com.longge.spring.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.longge.spring.controller.UserController;

/**
 * 数据字典
 * @author Administrator
 *
 */
public class Dictionary {
	
	Logger log=Logger.getLogger(this.getClass());
	private Map dictionaryMap=new HashMap<>();
	public void setDictionary(Map map){
		this.dictionaryMap=map;
	}
	public Map getValue(Object key){
		return (Map) dictionaryMap.get(key);
	}
	
	public List<Map<String, Object>> transferData( List<Map<String, Object>>  data,String... keys){
		if(data==null||data.size()==0) return data;
		try {
			for(Map<String, Object> m:data){
				for(String key:keys){
					String object = (String) m.get(key);
					m.put(key, getValue(key).get(object));
				}
			}
		} catch (Exception e) {
			log.info("转换失败，请检查Dictionary.json 或者初始化");
		}
		return data;
	}

}
