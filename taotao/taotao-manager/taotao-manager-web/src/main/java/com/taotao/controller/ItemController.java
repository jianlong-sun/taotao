package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ser.std.NumberSerializers.IntegerSerializer;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;


/**
 * 
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * <p>Company: www.taotao.com</p> 
 * @author	孙建隆
 * @date	2017年2月15日下午7:44:21
 * @version 1.0
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId)
	{	
		
		TbItem tbItem=itemService.getItemById(itemId); 
		return tbItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDateGridResult getItemList(Integer page,Integer rows)
	{
		EUDateGridResult result =itemService.getItemList(page, rows);
		return result;
	}
	
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createItem(TbItem item,String desc,String itemParams) throws Exception
	{
		TaotaoResult result = itemService.createItem(item,desc,itemParams);
		return result;
	}
}
	
	
