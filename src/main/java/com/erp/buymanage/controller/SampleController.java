package com.erp.buymanage.controller;

import com.erp.buymanage.security.dto.AuthMemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/")
    public String index(){
        return "redirect:/sample/all";
    }

    @GetMapping("/all")
    public void exAll(){
        log.info("exAll 모든사용자접속.................................."); //로그인을 하지 않아도 접속가능
    }

//    @GetMapping("/member")
//    public void exMember(){
//        log.info("exMember 회원접속.................................."); //로그인을 회원만 접속가능
//    }
    @GetMapping("/member")
    public void exMember(@AuthenticationPrincipal AuthMemberDTO authMember){
        log.info("exMember..........");
        log.info("-------------------------------");
        log.info(authMember);
    }

    @GetMapping("/admin")
    public void exAdmin(@AuthenticationPrincipal AuthMemberDTO authMember){
        log.info("exAdmin 관리자접속.................................."); //관리자 접속가능
        log.info("-------------------------------");
        log.info(authMember);

    }

}
