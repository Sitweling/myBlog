package com.sinn.controller.admin;

import com.sinn.pojo.Blog;
import com.sinn.pojo.User;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

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
    private  static final String REDIRECT_LIST ="redirect:/admin/blogs";

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
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());

        model.addAttribute("blog",new Blog());
        return INPUT;
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(Model model,@PathVariable Long id){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return INPUT;
    }

    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession session, RedirectAttributes attributes){
        User user = (User) session.getAttribute("user");
        blog.setUser(user);
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog saveBlog = blogService.saveBlog(blog);
        if(saveBlog!=null){
            attributes.addFlashAttribute("msg","操作成功 ");
        }else{
            attributes.addFlashAttribute("msg","操作失败");
        }
        return REDIRECT_LIST;
    }


    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("msg","删除成功");
        return REDIRECT_LIST;
    }

}
