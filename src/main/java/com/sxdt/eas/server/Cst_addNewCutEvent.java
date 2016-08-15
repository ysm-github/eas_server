package com.sxdt.eas.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxit.common.util.SIDValueGenerator;

import net.sf.json.JSONObject;


//2.3	客户事件新增
public class Cst_addNewCutEvent extends HttpServlet {
	private String resultJson;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	private String cmd="/crm/customer/addNewCutEvent.do";
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		init();
		response.setCharacterEncoding("utf-8");
		String data = resultJson;
		String sid=SIDValueGenerator.getInstance().generateValue();
		data=Utils.encipherRequestJsonMessage(App.appKey, App.appToken, App.appid, cmd, App.version, sid, data);
		response.setContentType("application/json;charset=utf-8");
		response.setContentLength(data.getBytes("UTF-8").length);
		PrintWriter pw = response.getWriter();
		pw.print(data);
		pw.flush();
		pw.close();
	}
	public void init() throws ServletException {
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("errcode", 0);
		data.put("errmsg", "成功");
		Map<String, Object> content=new HashMap<String, Object>();
		content.put("myGUID", UUID.randomUUID().toString().toUpperCase());
		data.put("content", content);
		
		JSONObject respJson=JSONObject.fromObject(data);
		this.resultJson=respJson.toString();
//		this.resultJson=this.getInitParameter("resultJson");
	}

}
