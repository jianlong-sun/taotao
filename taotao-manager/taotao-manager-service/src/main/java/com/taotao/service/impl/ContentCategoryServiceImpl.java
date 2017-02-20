package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

/**
 * <p>Title: ContentCategoryServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.taotao.com</p> 
 * @author	孙建隆
 * @date	2017年2月19日下午2:14:50
 * @version 1.0
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		//根据parentid查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			//创建一个节点
			EUTreeNode node = new EUTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			
			resultList.add(node);
		}
		return resultList;
	}
	@Override
	public TaotaoResult insertContentCategory(long parentId, String name) {
		//创建一个pojo
				TbContentCategory contentCategory = new TbContentCategory();
				contentCategory.setName(name);
				contentCategory.setIsParent(false);
				//'状态。可选值:1(正常),2(删除)',
				contentCategory.setStatus(1);
				contentCategory.setParentId(parentId);
				contentCategory.setSortOrder(1);
				contentCategory.setCreated(new Date());
				contentCategory.setUpdated(new Date());
				//添加记录
				contentCategoryMapper.insert(contentCategory);
				//查看父节点的isParent列是否为true，如果不是true改成true
				TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
				//判断是否为true
				if(!parentCat.getIsParent()) {
					parentCat.setIsParent(true);
					//更新父节点
					contentCategoryMapper.updateByPrimaryKey(parentCat);
				}
				//返回结果
				return TaotaoResult.ok(contentCategory);
	}
	@Override
	public TaotaoResult deleteContentCategory(long parentId, long Id) {
		//根据id删除列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(Id);
		//删除记录
		contentCategoryMapper.deleteByExample(example);
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		if(list.size() == 0)
		{
			TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
			parentCat.setIsParent(false);
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult updateContentCategory(long Id, String name) {
		TbContentCategoryExample example =new TbContentCategoryExample();
		Criteria criteria =example.createCriteria();
		criteria.andIdEqualTo(Id);
		//获得数据
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		TbContentCategory tb =list.get(0);
		System.out.println(list.size()+"=======");
		tb.setName(name);
		contentCategoryMapper.updateByPrimaryKey(tb);
		return TaotaoResult.ok();
	}

}
