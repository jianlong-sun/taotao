package com.taotao.service;

import com.taotao.common.util.EasyUIResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {
	TaotaoResult gertItemByCId(long cid);
	TaotaoResult  insertItemParam(TbItemParam itemParam);
	EasyUIResult getItemParamList(Integer page ,Integer rows);
}
