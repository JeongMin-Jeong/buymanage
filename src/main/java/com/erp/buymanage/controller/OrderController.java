package com.erp.buymanage.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@Log4j2
public class OrderController {

    @GetMapping("/")
    public String index(){
        return "redirect:/order/list";
    }

    @GetMapping("/list")
    public void list(){
        log.info("list................");
    }
}
