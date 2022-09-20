package com.xiwang.coolshark.controller;

import com.xiwang.coolshark.entity.News;

import com.xiwang.coolshark.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Value("${dirPath}")
    private String dirPath;
    @Autowired
    private NewsMapper newsMapper;

    @RequestMapping("/insert")
    public int insert(@RequestBody News news) {
        newsMapper.insert(news);
        return 1;
    }


    @RequestMapping("/select")
    public List<News> select() {
        return newsMapper.select();
    }

    @RequestMapping("/selectForIndex")
    public List<News> selectForIndex() {
        return newsMapper.selectForIndex();
    }

    @RequestMapping("/selectByIdForUpdate")
    public News selectByIdForUpdate(Integer id, HttpSession session) {
        return newsMapper.selectByIdForUpdate(id);
    }


    @RequestMapping("/update")
    public int update(@RequestBody News news) {
        newsMapper.update(news);
        return 1;
    }

    @RequestMapping("/delete")
    public void delete(Integer id, String url) {
        new File(dirPath + url).delete();
        newsMapper.deleteById(id);
    }

    @RequestMapping("/remove")
    public void remove(String url) {
        new File(dirPath + url).delete();
    }

}
