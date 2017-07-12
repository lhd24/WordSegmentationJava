package com.eastday.utils;

public class HttpTools {
	public static String htmlDecode(String str){
		String newString = "";
		try{
			newString = new String(str.getBytes("iso-8859-1"),"utf-8");
		} catch(Exception e){
			e.printStackTrace();
		}
		return newString;
	}
}
