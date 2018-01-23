package com.taotao.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: Jay
 * @Date: Created in 22:14 2017/12/29
 * @Modified By:
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public TaotaoResult insertContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        //插入数据
        contentMapper.insert(content);

        return TaotaoResult.ok();
    }

}
