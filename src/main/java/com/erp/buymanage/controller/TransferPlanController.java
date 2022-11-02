package com.erp.buymanage.controller;

import com.erp.buymanage.dto.*;
import com.erp.buymanage.security.dto.AuthMemberDTO;
import com.erp.buymanage.service.ProductService;
import com.erp.buymanage.service.TransferPlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/transferPlan")
@Log4j2
@RequiredArgsConstructor // 자동 주입을 위한 어노테이션
public class TransferPlanController {

    private final TransferPlanService service;
    private final ProductService productService;

    @GetMapping("/")
    public String index(){
        log.info(">>>>> TransferPlanController(index)");
        return "redirect:/transferPlan/list";
    }

    @GetMapping("/list")
    public void list(TransferPlanPageRequestDTO transferPlanPageRequestDTO, Model model, @AuthenticationPrincipal AuthMemberDTO authMember){
        log.info(">>>>> TransferPlanController(list)");
        model.addAttribute("result", service.getList(transferPlanPageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){
        log.info(">>>>> TransferPlanController(register GetMapping)");
    }

    @PostMapping("/register")
    public String registerContract(TransferPlanDTO dto, RedirectAttributes redirectAttributes){
        log.info(">>>>> TransferPlanController(register PostMapping)");
        Long tno = service.register(dto);
        redirectAttributes.addFlashAttribute("tno", tno);
        return "redirect:/transferPlan/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(long tno, @ModelAttribute("requestDTO") TransferPlanPageRequestDTO transferPlanPageRequestDTO, Model model){
        log.info(">>>>> TransferPlanController(read,modify GetMapping)");
        TransferPlanDTO dto = service.read(tno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String modify(TransferPlanDTO dto, @ModelAttribute("requestDTO") TransferPlanPageRequestDTO transferPlanPageRequestDTO, RedirectAttributes redirectAttributes){
        log.info(">>>>> TransferPlanController(modify PostMapping)");
        service.modify(dto);
        redirectAttributes.addAttribute("page", transferPlanPageRequestDTO.getPage());
        redirectAttributes.addAttribute("tno", dto.getTno());
        return "redirect:/transferPlan/read";
    }

    @PostMapping("/remove")
    public String remove(long tno, RedirectAttributes redirectAttributes){
        log.info(">>>>> TransferPlanController(remove PostMapping)");
        service.remove(tno);
        redirectAttributes.addFlashAttribute("tno", tno);
        return "redirect:/transferPlan/list";
    }

    @GetMapping("/popup")
    public void popup(ProductPageRequestDTO productPageRequestDTO, Model model) {
        log.info(">>>>> TransferPlanController(popup GetMapping)");
        model.addAttribute("result", productService.getList(productPageRequestDTO));
    }

    @GetMapping("/modalread")
    @ResponseBody
    public TransferPlanDTO modalread(@RequestParam Map<String, Object> param) {
        log.info(">>>>> TransferPlanController(modalread GetMapping)");

        String str = (String)param.get("tno");
        long tno = Long.parseLong(str);
        TransferPlanDTO dto = service.read(tno);
        return dto;
    }

    @PostMapping("/completeSave")
    public String completeSave(TransferPlanDTO dto) {
        log.info(">>>>> TransferPlanController(completeSave PostMapping)");
        dto.setTstate("완료");
        service.modify(dto);
        return "redirect:/transferPlan/list";
    }
}

