package com.taotao.service;

import com.taotao.common.pojo.PictureResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Jay
 * @Date: Created in 22:36 2017/12/20
 * @Modified By:
 */
public interface PictureService {

    PictureResult uploadPic(MultipartFile picFile);

}
