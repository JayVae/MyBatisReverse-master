package com.taotao.portal.controller;

import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Jay
 * @Date: Created in 23:04 2017/12/25
 * @Modified By:
 */
@Controller
public class IndexController {

    @Autowired
    private ContentService contentService;


    @RequestMapping("/index")
    public String showIndex(Model model) {
        String  json = contentService.getAdList();
        model.addAttribute("ad1",json);

        return "index";
    }

}
