package com.erp.buymanage.controller;

import com.erp.buymanage.dto.InspectionDTO;
import com.erp.buymanage.security.dto.AuthMemberDTO;
import com.erp.buymanage.service.InspectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inspection")
@Log4j2
@RequiredArgsConstructor // 자동 주입을 위한 어노테이션
public class InspectionController {
    private final InspectionService service;
    //    private final ProductService productService;
    //    private final PartnerService partnerService;
    @GetMapping("/")
    public String index(){
        log.info(">>>>> InspectionController(index)");
        return "redirect:/Inspection/planreg";
    }

    @GetMapping("/list")
    //public void list(Model model, @AuthenticationPrincipal AuthMemberDTO authMember){
    public void list(@AuthenticationPrincipal AuthMemberDTO authMember){
        log.info(">>>>> InspectionController(list)");
        //model.addAttribute("result", service.getList(contractPageRequestDTO));
    }

    @GetMapping("/planreg")
    public void register(){
        log.info(">>>>> InspectionController(plan register GetMapping)");
    }

    @PostMapping("/planreg")
    public String register(InspectionDTO dto, RedirectAttributes redirectAttributes){
        log.info(">>>>> InspectionController(plan register PostMapping)");
        Long ino = service.register(dto);
        redirectAttributes.addFlashAttribute("msg", ino);
        return "redirect:/inspection/planread";
    }

    @GetMapping("/planread")
    public void read(){
        log.info(">>>>> InspectionController(plan read GetMapping)");
    }


}


