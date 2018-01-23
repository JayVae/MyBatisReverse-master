package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

/**
 * @Author: Jay
 * @Date: Created in 19:36 2017/12/29
 * @Modified By:
 */
public interface ContentCategoryService {

    List<EasyUITreeNode> getContentCatList(Long parentId);
    TaotaoResult insertCatgory(Long parentId,String name);

}
