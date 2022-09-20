package com.xiwang.coolshark.controller;

import com.xiwang.coolshark.entity.Banner;
import com.xiwang.coolshark.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Value("${dirPath}")
    private String dirPath;
    @Autowired
    private BannerMapper bannerMapper;

    @RequestMapping("/select")
    public List<Banner> select() {
        return bannerMapper.select();
    }

    @RequestMapping("/insert")
    public int insert(String url) {
        bannerMapper.insert(url);
        if (bannerMapper.selectByUrl(url) == null) {
            return 3;
        }
        return 1;
    }

    @RequestMapping("/delete")
    public void delete(Integer id) {
        String url=bannerMapper.selectUrlById(id);
        new File(dirPath+url).delete();
        bannerMapper.deleteById(id);

    }
}
