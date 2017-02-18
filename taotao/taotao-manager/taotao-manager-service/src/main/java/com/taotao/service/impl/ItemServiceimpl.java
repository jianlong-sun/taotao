package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.util.IDUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
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
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
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

	@Override
	public EUDateGridResult getItemList(int page, int rows) {
		TbItemExample example =new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> list  = itemMapper.selectByExample(example);
		EUDateGridResult result =new EUDateGridResult();
		result.setRows(list); 
		PageInfo<TbItem> pageInfo =new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult createItem(TbItem  item,String desc,String itemParam) throws Exception {
		//item补全
		//生成商品id
		Long itemId =	IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//插入到数据库
		itemMapper.insert(item);
		TaotaoResult result =insertItemDesc(itemId, desc);
		if(result.getStatus() != 200)
		{
				throw new Exception();
		}
		//添加规格参数
		result = insertItemParamItem(itemId, itemParam);
		if(result.getStatus() != 200)
		{
				throw new Exception();
		}
		return TaotaoResult.ok();
	}
	
	private TaotaoResult insertItemDesc(Long itemId , String desc)
	{
		TbItemDesc itemDesc =new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}
	private TaotaoResult insertItemParamItem(Long itemId,String itemParam){
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		//插入数据
		itemParamItemMapper.insert(itemParamItem);
		return TaotaoResult.ok();
	}
}
