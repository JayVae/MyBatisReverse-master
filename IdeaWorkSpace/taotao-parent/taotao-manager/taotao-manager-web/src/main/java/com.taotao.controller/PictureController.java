package com.taotao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.pojo.PictureResult;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传controller
 * @Author: Jay
 * @Date: Created in 22:46 2017/12/20
 * @Modified By:
 */
@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile) throws JsonProcessingException {
        PictureResult result = pictureService.uploadPic(uploadFile);
        //String json= JsonUtils.objectToJson(result);
        ObjectMapper MAPPER = new ObjectMapper();
        String json= MAPPER.writeValueAsString(result);
        return json;
    }


}
