package com.sinn.controller;

import com.sinn.pojo.Type;
import com.sinn.service.BlogService;
import com.sinn.service.TypeService;
import com.sinn.utils.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/17
 */
@Controller
public class TypeShowController {

    @Autowired
    TypeService typeService;

    @Autowired
    BlogService blogService;

    @GetMapping("types/{id}")
    public String types(@PathVariable Long id, @PageableDefault(size = 8,sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model){
        List<Type> typeList=typeService.listTypeTop(1000);
        //id=-1说明是从首页导航点进来的
        if(id==-1){
            id = typeList.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types",typeList);
        model.addAttribute("page",blogService.listBlog(pageable,blogQuery));
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
