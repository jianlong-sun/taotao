package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.util.EasyUIResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;


/**
 * <p>Title: ItemParamServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.taotao.com</p> 
 * @author	孙建隆
 * @date	2017年2月18日上午10:04:25
 * @version 1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
	
		@Autowired
		private TbItemParamMapper itemParamMapper;
	@Override
	public TaotaoResult gertItemByCId(long cid) {
		TbItemParamExample example =new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		//判断查询结果
		if(list !=null &&list.size() > 0)
		{
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}
	@Override
	public EasyUIResult getItemParamList(Integer page, Integer rows) {
		//分页处理
				PageHelper.startPage(page, rows);
				//查询规格列表
				List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
				//取分页信息
				PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
				//返回结果
				EasyUIResult result = new EasyUIResult(pageInfo.getTotal(), list);
				
				return result;
	}
	

}
