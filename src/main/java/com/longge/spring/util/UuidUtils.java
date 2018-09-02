package com.longge.spring.util;

import java.util.UUID;

public class UuidUtils {
	public static String getUUID32(){
	    String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
	    return uuid;
	}

}
