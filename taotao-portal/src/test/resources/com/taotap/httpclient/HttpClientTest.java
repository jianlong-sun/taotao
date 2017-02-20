package com.taotap.httpclient;

import org.junit.Test;

public class HttpClientTest {
	@Test
	public void doGet() throws Exception {
		//创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个GET对象
		HttpGet get = new HttpGet("http://www.sogou.com");
		//执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		//取响应的结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		//关闭httpclient
		response.close();
		httpClient.close();
	}
	@Test
	public void doGetWithParam() throws Exception{
		//创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个uri对象
		URIBuilder uriBuilder = new URIBuilder("http://www.sogou.com/web");
		uriBuilder.addParameter("query", "花千骨");
		HttpGet get = new HttpGet(uriBuilder.build());
		//执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		//取响应的结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		//关闭httpclient
		response.close();
		httpClient.close();
	}
}
