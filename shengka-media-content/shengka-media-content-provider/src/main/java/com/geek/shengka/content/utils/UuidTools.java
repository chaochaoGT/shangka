package com.geek.shengka.content.utils;

import java.util.UUID;

 
public class UuidTools {
 
    public static String getUUIDString() {
    	   String id = UUID.randomUUID().toString();
    	   id = id.replace("-", "");
    	   return id;
    	}
}