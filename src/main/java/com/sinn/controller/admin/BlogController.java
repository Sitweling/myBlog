package com.sinn.controller.admin;

import com.sinn.pojo.Blog;
import com.sinn.service.BlogService;
import com.sinn.service.TagService;
import com.sinn.service.TypeService;
import com.sinn.utils.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/14
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private  static final String INPUT="admin/blogs-input";
    private  static final String LIST="admin/blogs";
    private  static final String DRDORECT="redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;


    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        System.out.println("查询了");
        return "admin/blogs :: blogList";
    }


    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("blog",new Blog());
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        return INPUT;
    }
}
