package com.taotao.controller;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.EasyUIResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;

/**
 * <p>Title: ItemParamController</p>
 * <p>Description:商品规格参数管理 </p>
 * <p>Company: www.taotao.com</p> 
 * @author	孙建隆
 * @date	2017年2月18日上午10:12:50
 * @version 1.0
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	@Autowired
	private ItemParamService ItemParamService;
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaotaoResult getItemParamByCid(@PathVariable Long itemCatId )
	{
		 TaotaoResult result = ItemParamService.gertItemByCId(itemCatId);
		 return result;
	}
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable Long cid ,String paramData )
	{
		TbItemParam itemParam =new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		TaotaoResult result = ItemParamService.insertItemParam(itemParam);
		return  result;
	}
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIResult getItemParamList(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "30") Integer rows) throws Exception {
		//查询列表
		EasyUIResult result = ItemParamService.getItemParamList(page, rows);
		return result;
	}
}
