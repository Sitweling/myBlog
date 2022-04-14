package com.sinn.controller.admin;

import com.sinn.pojo.Type;
import com.sinn.service.TypeService;
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

import java.time.Year;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/14
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;



    //分页查询
    @GetMapping("/types")
    public String types(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "/admin/types";
    }

    //新增
    @GetMapping("/types/input")
    public String toAddType(Model model){
        model.addAttribute("type", new Type());   //返回一个type对象给前端th:object
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String toEditType(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getType(id));
        return "/admin/types-input";
    }

    //提交
    @PostMapping("/types")
    public String post(Type type , RedirectAttributes attributes){
        Type byName = typeService.getTypeByName(type.getName());
        if(byName != null){
            attributes.addFlashAttribute("msg", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }else {
            attributes.addFlashAttribute("msg", "添加成功");
        }
        typeService.saveType(type);
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editType(@PathVariable Long id,Type type,RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 != null){
            attributes.addFlashAttribute("msg", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }else {
            attributes.addFlashAttribute("msg", "修改成功");
        }
        typeService.updateType(id, type);
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        System.out.println("进入删除方法");
        typeService.deleteType(id);
        attributes.addFlashAttribute("msg","删除成功！");
        return "redirect:/admin/types";
    }
}
