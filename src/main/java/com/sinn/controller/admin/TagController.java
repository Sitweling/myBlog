package com.sinn.controller.admin;

import com.sinn.pojo.Tag;
import com.sinn.pojo.Type;
import com.sinn.service.TagService;
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

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/15
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    //分页查询
    @GetMapping("/tags")
    public String types(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "/admin/tags";
    }

    //新增(之前需要查询）
    @GetMapping("/tags/input")
    public String toAddTag(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String toEditTag(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "/admin/tags-input";
    }

    //提交
    @PostMapping("/tags")
    public String post(Tag tag, RedirectAttributes attributes){
        Tag tagByName = tagService.getTagByName(tag.getName());
        if(tagByName!=null){
            attributes.addFlashAttribute("msg","不能添加重复分类");
            return "redirect:/admin/tags/input";
        }else{
            attributes.addFlashAttribute("msg","添加成功！");
        }
        tagService.saveTag(tag);
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String editTag(@PathVariable Long id, Tag tag,RedirectAttributes attributes){
        Tag tagByName = tagService.getTagByName(tag.getName());
        if(tagByName!=null){
            attributes.addFlashAttribute("msg","不能添加重复分类");
            return "redirect:/admin/tags/input";
        }else{
            attributes.addFlashAttribute("msg","修改成功！");
        }
        tagService.updateTag(id,tag);
        return "redirect:/admin/tags";
    }

    //删除
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("msg","删除成功！");
        return "redirect:/admin/tags";
    }
}
