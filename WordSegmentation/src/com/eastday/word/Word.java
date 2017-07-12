package com.eastday.word;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import com.eastday.utils.JsonReader;

/***
 * 
 * @author root
 *
 */
public class Word extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		 /** 设置响应头允许ajax跨域访问 **/  
        response.setHeader("Access-Control-Allow-Origin", "*");  
        /* 星号表示所有的异域请求都可以接受， */  
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");  
		
        JSONArray resultArray = JsonReader.receivePost(request);  
        
		//创建JSONArray数组，并将json添加到数组
        JSONArray array = new JSONArray();
		for (int i = 0; i < resultArray.length(); i++) {
			JSONObject jsonObj = resultArray.optJSONObject(i);
			String word = jsonObj.getString("article");;
	        StringReader sr=new StringReader(word);  
	        IKSegmenter ik=new IKSegmenter(sr, true);  
	        Lexeme lex=null;  
	        String res = "";
	        while((lex=ik.next())!=null){  
	        	res = res + lex.getLexemeText()+" ";
	        }  
	        if (res.length() > 1) {
	        	res = res.substring(0, res.length()-1);
			}  
			//创建JSONObject对象
	        JSONObject json = new JSONObject();
	        
	        //向json中添加数据
	        json.put("result", res);
	        array.put(json);
		}
        
        //转换为字符串
        String jsonStr = array.toString();
        
        PrintWriter out = response.getWriter();
        out.print(jsonStr);  
		out.flush();
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
