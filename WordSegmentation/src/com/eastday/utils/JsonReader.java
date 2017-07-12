package com.eastday.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

public class JsonReader {
	public static JSONArray receivePost(HttpServletRequest request) throws IOException, UnsupportedEncodingException {  
		  
        // 读取请求内容  
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));  
        String line = null;  
        StringBuilder sb = new StringBuilder();  
        while ((line = br.readLine()) != null) {  
            sb.append(line);  
        }  
        
      //将json字符串转换为json对象  
        JSONArray json=JSONArray.fromString(sb.toString());  
        return json;  
    }  
}
