package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Jay
 * @Date: Created in 22:37 2017/12/15
 * @Modified By:
 */
@Service
public interface ItemCatService {

    List<EasyUITreeNode> getItemCatList(long parentId);

}
