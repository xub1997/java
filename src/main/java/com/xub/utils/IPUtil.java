package com.xub.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class IPUtil {

	/**
	 * 获取IP地址
	 *
	 * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
	 */
	public static String getIpAddress(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}

	/**
	 *  
	 * 获取IP+[IP所属地址]
	 *
	 * @param request
	 * @return
	 */
	public static String getIpBelongAddress(HttpServletRequest request) {

		String ip = getIpAddress(request);
		String belongIp = getIPbelongAddress(ip);

		return ip + belongIp;
	}

	/**
	 *  
	 * 获取IP所属地址
	 *
	 * @param ip
	 * @return
	 */
	public static String getIPbelongAddress(String ip) {

		String ipAddress = "[]";
		try {
//淘宝提供的服务地址
			String context = call("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
			JSONObject fromObject = JSONObject.parseObject(context);
			String code = fromObject.getString("code");
			if (code.equals("0")) {
				JSONObject jsonObject = fromObject.getJSONObject("data");
				ipAddress = "[" + jsonObject.get("country") + "/" + jsonObject.get("city") + "]";
			}
		} catch (Exception e) {

			log.error("获取IP所属地址出错", e);
			e.printStackTrace();
		}
		return ipAddress;
	}

	/**
	 *  
	 * 描述：获取Ip所属地址
	 *
	 * @param urlStr
	 * @return
	 */
	public static String call(String urlStr) {

		try {

			URL url = new URL(urlStr);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

			httpCon.setConnectTimeout(3000);
			httpCon.setDoInput(true);
			httpCon.setRequestMethod("GET");

			int code = httpCon.getResponseCode();

			if (code == 200) {
				return streamConvertToSting(httpCon.getInputStream());
			}
		} catch (Exception e) {
			log.error("获取IP所属地址出错", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *  
	 * 描述：将InputStream转换成String
	 *
	 * @param is
	 * @return
	 */
	public static String streamConvertToSting(InputStream is) {

		String tempStr = "";
		try {
			if (is == null) {
				return null;
			}
			ByteArrayOutputStream arrayOut = new ByteArrayOutputStream();
			byte[] by = new byte[1024];
			int len = 0;
			while ((len = is.read(by)) != -1) {
				arrayOut.write(by, 0, len);
			}
			tempStr = new String(arrayOut.toByteArray());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempStr;
	}

	public static void main(String[] args) {


		String context = call("http://ip.taobao.com/service/getIpInfo.php?ip=120.192.182.1");

		JSONObject fromObject = JSONObject.parseObject(context);
		JSONObject jsonObject = fromObject.getJSONObject("data");
		System.out.println(fromObject);
		System.err.println(jsonObject.get("city"));
	}

}
