package com.taotao.service;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(long itemId);
	EUDateGridResult getItemList(int page,int rows);
	TaotaoResult createItem(TbItem tbItem,String desc,String itemParam) throws Exception;
}
