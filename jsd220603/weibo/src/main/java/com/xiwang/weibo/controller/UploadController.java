package com.xiwang.weibo.controller;


import com.xiwang.weibo.entity.Image;
import com.xiwang.weibo.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class UploadController {

    @Autowired
    private ImageMapper imgMapper;

    @RequestMapping("/upload")
    public String upload(MultipartFile pic) throws IOException {
        String fileName = pic.getOriginalFilename();

        String suffix = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffix;
        String dirPath = "C:/Users/XiWang/Desktop/testFile";
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        String filePath = dirPath + "/" + fileName;
        pic.transferTo(new File(filePath));
        return "/" + fileName;
    }


    @RequestMapping("/insert")
    public void insert(@RequestBody Image image) {
        System.out.println(image);
        imgMapper.insert(image);

    }

    @RequestMapping("/select")
    public List<Image> select() {
        return imgMapper.select();
    }

    @RequestMapping("/remove")
    public void remove(String url) {
        new File("C:/Users/XiWang/Desktop/testFile" + url).delete();
    }

    @RequestMapping("/del")
    public Integer delete(Integer id) {

        String urls = imgMapper.selectById(id).getUrls();
        imgMapper.deleteById(id);
        for (String url : urls.split(",")) {
            //删除文件
            new File("C:/Users/XiWang/Desktop/testFile" + url).delete();
        }
        if (imgMapper.selectById(id) != null) {
            return 1;
        }
        return 2;
    }

}
