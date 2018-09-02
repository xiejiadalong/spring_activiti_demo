package com.longge.spring.util;

import java.util.List;
import java.util.Map;

public class PageUtil {
	
	public static void handlePageParms(Map<String, Object> pageParms){
		int currentPage=(int) pageParms.get("currentPage");
		if(currentPage<=0){
			currentPage=1;
			pageParms.put("currentPage", currentPage);
		}
		int numberOfPages=(int) pageParms.get("numberOfPages");
		if(numberOfPages<=0){
			numberOfPages=5;
			pageParms.put("numberOfPages", numberOfPages);
		}
		pageParms.put("startNum", (currentPage-1)*numberOfPages);
	}
	
	
	public static List<Map<String, Object>> handlePageResult(List<Map<String, Object>> resData,int totle_record,Map<String, Object> pageParms){
		int numberOfPages=(int) pageParms.get("numberOfPages");
		if(resData.size()>0){
			int totalPages=totle_record/numberOfPages+((totle_record%numberOfPages)>0?1:0);
			resData.get(0).put("total_record", totle_record);
			resData.get(0).put("totalPages", totalPages);
		}
		return resData;
	}

}
