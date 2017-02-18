package com.taotao.rest.service.impl;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * <p>Title: ItemCatServiceImpl</p>
 * <p>Description:商品分类服务 </p>
 * <p>Company: www.taotao.com</p> 
 * @author	孙建隆
 * @date	2017年2月18日下午7:59:13
 * @version 1.0
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Override
	public CatResult getItemCatList() {
		CatResult catResult = new CatResult();
		//查询分类列表
		catResult.setData(getCatList(0));
		return catResult;
	}
	private List<?> getCatList(long parentId){
		TbItemCatExample example =new TbItemCatExample();
		Criteria criteria =example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List  resultList = new ArrayList<>();
		//向list中添加节点
		int count = 0;
		for(TbItemCat tbItemCat : list)
		{
			if(tbItemCat.getIsParent())
			{
				CatNode catNode =new CatNode();
				if(parentId ==0)
				{
					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				} else  {
					catNode.setName(tbItemCat.getName());
	
				}
				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
				catNode.setItem(getCatList(tbItemCat.getId()));
				resultList.add(catNode);
				count++;
				//如果是叶子节点
				//判断
				//第一层只取14条记录
				if(parentId ==0 && count >=14)
				{
					break;
				}
			}else{
				resultList.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
			}

		}
		return resultList;
	
	}

}
