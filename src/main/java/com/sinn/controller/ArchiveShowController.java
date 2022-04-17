package com.sinn.controller;

import com.sinn.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/17
 */
@Controller
public class ArchiveShowController {

    @Autowired
    BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogCount",blogService.countBlog());
        return  "/archives";
    }
}
