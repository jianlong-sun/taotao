package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class FTPTest {
	@Test
	public void testFtpClient() throws Exception 
	{
		//常见一个链接、
		FTPClient ftpClient =new FTPClient();
		ftpClient.connect("192.168.1.59",21);
		//登录服务器,使用用户名和密码
		ftpClient.login("ftpuser", "ftpuser");
		//上传文件
		//设置上传路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		//第一个参数。文档名
		//第二个参数：上传文档的imputStream
		FileInputStream inputStream =new FileInputStream(new File("D:\\sunjianlong.jpg"));
		boolean result=ftpClient.storeFile("sun1.jpg", inputStream);
		System.out.println("sunjianlong");
		System.out.println(result);
		//关闭
		ftpClient.logout();
	}
}
