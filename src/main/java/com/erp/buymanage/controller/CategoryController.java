package com.erp.buymanage.controller;

import com.erp.buymanage.dto.CategoryPageRequestDTO;
import com.erp.buymanage.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category/")
@Log4j2
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service; //final선언 for @RequiredArgsConstructor

    @GetMapping("/")
    public String index(){
        return "redirect:/guestbook/list";
    }

//    @GetMapping({"/","/list"})
//    public String list(){
//        log.info("-category list---------------");
//        return "/category/list";
//    }
    @GetMapping("/list")
    public void list(CategoryPageRequestDTO categoryPageRequestDTO, Model model){
        log.info("list................" + categoryPageRequestDTO);
        model.addAttribute("result", service.getlist(categoryPageRequestDTO));
        model.addAttribute("result2", service.getlist2(categoryPageRequestDTO));
    }

}







