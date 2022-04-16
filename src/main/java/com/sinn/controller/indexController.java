package com.sinn.controller;

import com.sinn.service.BlogService;
import com.sinn.service.TagService;
import com.sinn.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/6
 */
@Controller
public class indexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("blog")
    public String blog(){
        return "blog";
    }

    //分页数据
    @RequestMapping("/")
    public String index(@PageableDefault(size = 8,sort = {"updateTime"}, direction = Sort.Direction.DESC)Pageable pageable, Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(8));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(5));
        return "/index";
    }
}
