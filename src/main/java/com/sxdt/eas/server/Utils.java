package com.sxdt.eas.server;

import java.nio.charset.Charset;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.sxit.common.security.EncryptionUtils;

public class Utils {
	public static final Logger log = Logger.getLogger(Utils.class);
	/**
	 * 构造开放接口加密请求包
	 * @param appKey   RC4加密秘钥
	 * @param appToken SHA1认证时用到的Token
	 * @param appid	   应用编号
	 * @param cmd      接口名称，参照开发接口规范文档
	 * @param version  协议版本号，默认"2.0"
	 * @param sid	   消息编号，规则开发接口规范文档
	 * @param dataJsonStr 协议数据包
	 * @return 组装好的json协议包
	 */
	public static String encipherRequestJsonMessage(String appKey,String appToken,String appid,String cmd,String version,String sid,String dataJsonStr){
		//加密过程：Sha1[Base64[rc4[源]]]
		String message=null;
		JSONObject header=new JSONObject();
		header.put("appid", appid);
		header.put("timestamp", now());
		header.put("sid",sid);
		header.put("cmd",cmd);
		header.put("version",version);
		log.debug("EAS接口加密明文："+dataJsonStr);
		byte[] in=dataJsonStr.getBytes(Charset.forName("utf-8"));
		//rc4加密
		EncryptionUtils.rc4(appKey, in);
		JSONObject requestMessage=new JSONObject();
		//密文使用Base64保存
		String encryBase64Data=EncryptionUtils.base64Encode(in);
		//对base64密文,计算sha1摘要
		String dataCode=EncryptionUtils.sha1(encryBase64Data);
		header.put("dataCode",dataCode);
		//组装认证检验码
		String authCode=EncryptionUtils.signature(appid, sid, appToken, dataCode);
		header.put("authCode",authCode);
		requestMessage.put("header", header);
		requestMessage.put("data",encryBase64Data);
		String encryText=requestMessage.toString();
		log.debug("EAS接口加密密文："+encryText);
		return encryText;
	}
	/**
	 * 解密请求包
	 * @param appKey
	 * @param appToken
	 * @param requestCipherText
	 * @return data为明文格式的json对象
	 */
	public static JSONObject decipherRequestJsonMessage(String appKey,String appToken,String cipherText){
		return null;
	}
	public static long now() {
		return System.currentTimeMillis() / 1000;
	}
	public static void main(String[] args) {
		System.out.println(1);
	}
}
