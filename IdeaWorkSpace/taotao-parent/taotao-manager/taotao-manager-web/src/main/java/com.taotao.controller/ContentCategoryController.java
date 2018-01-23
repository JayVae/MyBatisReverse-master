package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: Jay
 * @Date: Created in 19:52 2017/12/29
 * @Modified By:
 */
@Controller
@RequestMapping("/content/category")

public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCatgoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(value="id", defaultValue="0")Long parentId) {
        List<EasyUITreeNode> list = contentCatgoryService.getContentCatList(parentId);
        return list;

    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createNode(Long parentId, String name) {
        TaotaoResult result = contentCatgoryService.insertCatgory(parentId, name);
        return result;
    }






}
