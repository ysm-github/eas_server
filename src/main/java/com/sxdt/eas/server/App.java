package com.sxdt.eas.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.lang.StringUtils;

public class App extends HttpServlet {

	private static final long serialVersionUID = 476114110049092158L;
	public static String appKey="app_key_401";
	public static String appToken="test_token_eas_2016";
	public static String appid="401";
	public static String version="2.0";
	
	public void init() throws ServletException {
		String key=this.getInitParameter("appKey");
		String token=this.getInitParameter("appToken");
		String id=this.getInitParameter("appid");
		if (StringUtils.isNotEmpty(key)) {
			this.appKey=key;
		}
		if (StringUtils.isNotEmpty(token)) {
			this.appToken=token;
		}
		if (StringUtils.isNotEmpty(id)) {
			this.appid=id;
		}
		System.out.println("appKey:"+appKey);
		System.out.println("appToken:"+appToken);
		System.out.println("appid:"+appid);
	}
}
