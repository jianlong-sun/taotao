package com.taotao.rest.controller;



import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * <p>Title: ItemCatController</p>
 * <p>Description:商品分类列表 </p>
 * <p>Company: www.taotao.com</p> 
 * @author	孙建隆
 * @date	2017年2月18日下午8:26:20
 * @version 1.0
 */
@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService ItemCatService;
/*	@RequestMapping(value="/itemcat/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback)
	{
		CatResult catResult = ItemCatService.getItemCatList();
		//System.out .println(catResult.toString()+"============");
		//把pojo转成字符串
		String json = JsonUtils.objectToJson(catResult);
		//拼装
		//System.out .println(json.toString()+"============");
		String result =callback +"("+json+");";
		return result;
	}*/
	@RequestMapping("/itemcat/list")
	@ResponseBody
	public Object getItemCatList(String callback)
	{
		CatResult catResult = ItemCatService.getItemCatList();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
}
