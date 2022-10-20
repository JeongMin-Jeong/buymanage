package com.erp.buymanage.controller;

import com.erp.buymanage.dto.PageRequestDTO2;
import com.erp.buymanage.dto.ProductDTO;
import com.erp.buymanage.security.dto.AuthMemberDTO;
import com.erp.buymanage.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/product")
@Log4j2
@RequiredArgsConstructor // 자동 주입을 위한 어노테이션
public class ProductController {

    private final ProductService service;

    @GetMapping("/")
    public String index(){
        log.info(">>>>> ProductController(\"/\")");

        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model, @AuthenticationPrincipal AuthMemberDTO authMember){
        log.info(">>>>> ProductController(list)");
        log.info(">>>>> authMember" + authMember);

    public void list(PageRequestDTO2 pageRequestDTO, Model model, @AuthenticationPrincipal AuthMemberDTO authMember){
        log.info(">>>>> ProductController(list)");
        model.addAttribute("result", service.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){
        log.info(">>>>> ProductController(register GetMapping)");
    }

    @PostMapping("/register")
    public String registerProduct(ProductDTO dto, RedirectAttributes redirectAttributes){
        log.info(">>>>> ProductController(register PostMapping)");

        Long pno = service.register(dto);

        redirectAttributes.addFlashAttribute("msg", pno);
        return "redirect:/product/list";
    }

    @GetMapping({"/read","/modify"})
    public void read(long pno, @ModelAttribute("requestDTO") PageRequestDTO2 requestDTO, Model model){

        log.info(">>>>> ProductController(read,modify GetMapping)");
        log.info(">>>>> pno: " + pno);

        ProductDTO dto = service.read(pno);

        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String modify(ProductDTO dto, @ModelAttribute("requestDTO") PageRequestDTO2 requestDTO, RedirectAttributes redirectAttributes){

        log.info(">>>>> ProductController(modify PostMapping)");

        service.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("pno", dto.getPno());

        return "redirect:/product/read";
    }

    @PostMapping("/remove")
    public String remove(long pno, RedirectAttributes redirectAttributes){
        log.info(">>>>> ProductController(remove PostMapping)");
        log.info(">>>>> pno: " + pno);

        service.remove(pno);

        redirectAttributes.addFlashAttribute("msg", pno);
        return "redirect:/product/list";
    }
}
