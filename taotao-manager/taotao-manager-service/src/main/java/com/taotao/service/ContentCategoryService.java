package com.taotao.service;

import java.util.List;


import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.util.TaotaoResult;

public interface ContentCategoryService {
	public List<EUTreeNode> getCategoryList(long parentId);
	TaotaoResult  insertContentCategory(long parentId, String name);
	TaotaoResult  deleteContentCategory(long parentId ,long Id );
	TaotaoResult  updateContentCategory(long Id ,String name );

}
