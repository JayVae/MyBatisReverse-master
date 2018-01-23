package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * @Author: Jay
 * @Date: Created in 16:21 2017/12/24
 * @Modified By:
 */
public interface ItemParamService {
    TaotaoResult getItemParamByCid(Long cid);
    TaotaoResult insertItemParam(Long cid,String paramData);
}
