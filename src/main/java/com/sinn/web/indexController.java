package com.sinn.web;

import com.sinn.exception.NotFoundBlogException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/6
 */
@Controller
public class indexController {
    @GetMapping("/")
    public String index(){
//        int i=9/0;
        String blog=null ;
        if(blog==null){
            throw  new NotFoundBlogException("博客不存在");
        }
        return "index";
    }
}
