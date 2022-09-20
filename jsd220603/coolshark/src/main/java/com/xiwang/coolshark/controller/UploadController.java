package com.xiwang.coolshark.controller;


import com.xiwang.coolshark.entity.Banner;
import com.xiwang.coolshark.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class UploadController {

    @Value("${dirPath}")
    private String dirPath;

    @Autowired
    private BannerMapper bannerMapper;

    @RequestMapping("/upload")
    public String upload(MultipartFile pic) throws IOException {
        String fileName = pic.getOriginalFilename();

        String suffix = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffix;
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        String filePath = dirPath + "/" + fileName;
        pic.transferTo(new File(filePath));
        return "/" + fileName;
    }


    @RequestMapping("/select")
    public List<Banner> select() {
        return bannerMapper.select();
    }

    @RequestMapping("/remove")
    public void remove(String url) {
        new File(dirPath + url).delete();
    }

    @RequestMapping("/del")
    public Integer delete(Integer id) {

        String urls = bannerMapper.selectById(id).getUrl();
        bannerMapper.deleteById(id);
        for (String url : urls.split(",")) {
            //删除文件
            new File(dirPath+ url).delete();
        }
        if (bannerMapper.selectById(id) != null) {
            return 1;
        }
        return 2;
    }

}
