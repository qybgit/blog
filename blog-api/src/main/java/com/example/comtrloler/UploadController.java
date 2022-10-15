package com.example.comtrloler;

import com.example.util.QiniuUtil;
import com.example.vo.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@RequestMapping("upload")
public class UploadController {
    @Resource
    QiniuUtil qiniuUtil;
    @PostMapping
    public Result upload(@RequestParam("image")MultipartFile file){
        String fileName= UUID.randomUUID()+"."+ StringUtils.substringAfterLast(file.getOriginalFilename(),".");
        boolean upload=qiniuUtil.upload(file,fileName);
        if (upload){
           return Result.success(QiniuUtil.url+fileName);
        }else{
            return Result.fail(1001,"上传失败");
        }


    }
}
