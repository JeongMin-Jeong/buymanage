package com.erp.buymanage.controller;

import com.erp.buymanage.dto.MemberPageRequestDTO;
import com.erp.buymanage.security.dto.AuthMemberDTO;
import com.erp.buymanage.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor // 자동 주입을 위한 어노테이션
public class MemberController {

    private final MemberService service;

    @GetMapping("/")
    public String index(){
        log.info(">>>>> MemberController(index)");
        return "redirect:/member/list";
    }

    @GetMapping("/list")
    public void list(MemberPageRequestDTO memberPageRequestDTO, Model model, @AuthenticationPrincipal AuthMemberDTO authMember){
        log.info(">>>>> MemberController(list)");
        model.addAttribute("result", service.getList(memberPageRequestDTO));
    }

}
