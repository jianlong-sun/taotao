package com.taotao.service;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	EUDateGridResult getContentList(int page,int rows,long categoryId);
	public TaotaoResult insertContent(TbContent content);
}
