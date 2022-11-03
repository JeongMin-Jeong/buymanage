package com.erp.buymanage.controller;

import com.erp.buymanage.dto.*;
import com.erp.buymanage.security.dto.AuthMemberDTO;
import com.erp.buymanage.service.ContractService;
import com.erp.buymanage.service.OrderService;
import com.erp.buymanage.service.PartnerService;
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
@RequestMapping("/contract")
@Log4j2
@RequiredArgsConstructor // 자동 주입을 위한 어노테이션
public class ContractController {

    private final ContractService service;
    private final ProductService productService;
    private final PartnerService partnerService;
    private final OrderService orderService;

    @GetMapping("/")
    public String index(){
        log.info(">>>>> ContractController(index)");
        return "redirect:/contract/list";
    }

    @GetMapping("/list")
    public void list(ContractPageRequestDTO contractPageRequestDTO, Model model, @AuthenticationPrincipal AuthMemberDTO authMember){
        log.info(">>>>> ContractController(list)");
        model.addAttribute("result", service.getList(contractPageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){
        log.info(">>>>> ContractController(register GetMapping)");
    }

    @PostMapping("/register")
    public String registerContract(ContractDTO dto, RedirectAttributes redirectAttributes){
        log.info(">>>>> ContractController(register PostMapping)");
        Long cno = service.register(dto);
        redirectAttributes.addFlashAttribute("msg", cno);
        return "redirect:/contract/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(long cno, @ModelAttribute("requestDTO") ContractPageRequestDTO contractPageRequestDTO, Model model){
        log.info(">>>>> ContractController(read,modify GetMapping)");
        ContractDTO dto = service.read(cno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String modify(ContractDTO dto, @ModelAttribute("requestDTO") ContractPageRequestDTO contractPageRequestDTO, RedirectAttributes redirectAttributes){
        log.info(">>>>> ContractController(modify PostMapping)");
        service.modify(dto);
        redirectAttributes.addAttribute("page", contractPageRequestDTO.getPage());
        redirectAttributes.addAttribute("cno", dto.getCno());
        return "redirect:/contract/read";
    }

    @PostMapping("/remove")
    public String remove(long cno, RedirectAttributes redirectAttributes){
        log.info(">>>>> ContractController(remove PostMapping)");
        service.remove(cno);
        redirectAttributes.addFlashAttribute("msg", cno);
        return "redirect:/contract/list";
    }

    @GetMapping("/popup")
    public void popup(ProductPageRequestDTO productPageRequestDTO, Model model) {
        log.info("product popup get...");
        log.info("(list)ProductPageRequestDTO : \" + productPageRequestDTO");
        model.addAttribute("result", productService.getList(productPageRequestDTO));
    }

    @GetMapping("/partnerList")
    public void partnerList(PartnerPageRequestDTO partnerPageRequestDTO, Model model, @AuthenticationPrincipal AuthMemberDTO authMember){
        log.info(">>>>> ContractController(partnerList)");
        model.addAttribute("result", partnerService.getList(partnerPageRequestDTO));
    }

    @GetMapping("/partnerPopup")
    public void partnerPopup(PartnerPageRequestDTO partnerPageRequestDTO, Model model) {
        log.info(">>>>> ContractController (partnerPopup)");
        model.addAttribute("result", partnerService.getList(partnerPageRequestDTO));
    }

    @GetMapping("/tradeprint")
    public void tradeprint(Long cno, String ocode,  @ModelAttribute("requestDTO") ContractPageRequestDTO contractPageRequestDTO, Model model){
        log.info(">>>>> ContractController(tradeprint GetMapping)");
        ContractDTO dto = service.read(cno);
        OrderDTO dto2 = orderService.read2(cno, ocode);
        model.addAttribute("dto", dto);
        model.addAttribute("dto2", dto2);
    }
}

