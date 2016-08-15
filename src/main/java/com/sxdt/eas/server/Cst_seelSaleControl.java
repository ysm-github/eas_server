package com.sxdt.eas.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxit.common.util.SIDValueGenerator;


//2.13	房产取消预留设置接口
public class Cst_seelSaleControl extends HttpServlet {
	private String resultJson;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	private String cmd="/crm/customer/seelSaleControl.do";
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		this.resultJson=this.getInitParameter("resultJson");
	}

}
