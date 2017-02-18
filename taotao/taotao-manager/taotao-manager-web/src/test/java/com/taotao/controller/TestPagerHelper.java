package com.taotao.controller;



import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class TestPagerHelper {
	@Test
	public void testPagerHelper(){
		//创建一个spring容器，从spring容器中获得mapper代理对象，执行查询分页
		ApplicationContext applicationContext =new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		TbItemMapper mapper=applicationContext.getBean(TbItemMapper.class);
		TbItemExample example=new TbItemExample();
		PageHelper.startPage(1, 10);
		List<TbItem> list=mapper.selectByExample(example);
		for(TbItem tbItem : list){
			System.out.println(tbItem.getTitle());
		}
		PageInfo<TbItem> pageInfo =new PageInfo<>(list);
		long total = pageInfo.getTotal();
		System.out.println("共有商品："+total);
		 
		
	}
}
