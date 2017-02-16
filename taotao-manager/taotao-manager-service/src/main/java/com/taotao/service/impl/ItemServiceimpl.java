package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

/**
 * <p>Title: ItemServicempl</p>
 * <p>Description: </p>
 * <p>Company: www.taotao.com</p> 
 * @author	孙建隆
 * @date	2017年2月15日下午7:27:10
 * @version 1.0
 */
@Service 
public class ItemServiceimpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		// TODO Auto-generated method stub
		//TbItem item=itemMapper.selectByPrimaryKey(itemId);
		TbItemExample example=new TbItemExample();
		//添加查询条件
		System.out.println("11111");
		Criteria criteria =example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list=itemMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			TbItem item =list.get(0);
			return item;
		}
		return null;
	}

}
