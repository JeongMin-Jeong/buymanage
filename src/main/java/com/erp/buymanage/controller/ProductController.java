package com.erp.buymanage.controller;

import com.erp.buymanage.security.dto.AuthMemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
@Log4j2
public class ProductController {

    @GetMapping("/")
    public String index(){
        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public void list(@AuthenticationPrincipal AuthMemberDTO authMember){
        log.info("list................");
        log.info("-------------------------------");
        log.info(authMember);
    }

}
