package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ContentService;

/**
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.taotao.com</p> 
 * @author	孙建隆
 * @date	2017年2月19日下午8:05:44
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	TbContentMapper contentMapper;
	@Override
	public EUDateGridResult getContentList(int page, int rows, long categoryId) {
		TbContentExample example =new TbContentExample();
		Criteria criteria =example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		PageHelper.startPage(page, rows);
		List<TbContent> list  = contentMapper.selectByExample(example);
		EUDateGridResult result =new EUDateGridResult();
		result.setRows(list); 
		PageInfo<TbContent> pageInfo =new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
		
	}
	@Override
	public TaotaoResult insertContent(TbContent content) {
		//补全pojo内容
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		
		return TaotaoResult.ok();
	}

}
