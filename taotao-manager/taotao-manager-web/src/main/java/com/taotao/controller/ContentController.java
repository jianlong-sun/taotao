package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	ContentService contentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDateGridResult getContentList(Integer page,Integer rows,long categoryId)
	{
		EUDateGridResult result =contentService.getContentList(page, rows, categoryId);
		return result;
	}
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content) {
		TaotaoResult result = contentService.insertContent(content);
		return result;
	}
	 
}
