package com.erp.buymanage.controller;

import com.erp.buymanage.dto.*;
import com.erp.buymanage.entity.OrderChart;
import com.erp.buymanage.service.ContractService;
import com.erp.buymanage.service.OrderChartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.erp.buymanage.service.OrderService;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
@Log4j2

public class OrderController {

    private final OrderService orderService;
    private final ContractService contractService;

    private final OrderChartService orderChartService;

    @GetMapping("/")
    public String index() {
        return "redirect:/order/list";
    }

    @GetMapping("/list")
    public void list(OrderPageRequestDTO orderPageRequestDTO, Model model) {
     log.info("list................" + orderPageRequestDTO);
        model.addAttribute("result", orderService.getList(orderPageRequestDTO));
    }

    @GetMapping("/register")
    public void register() {
        log.info("register get...");
    }

    @PostMapping("/register")
    public String registerPost(OrderDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto..." + dto);
        //새로 추가된 엔티티의 번호
        long ono = orderService.register(dto);

        redirectAttributes.addFlashAttribute("msg",ono);

        return "redirect:/order/list";
    }

    @GetMapping({"/read","/modify"})
    public void read(long ono, @ModelAttribute("requestDTO") OrderPageRequestDTO requestDTO, Model model) {
        log.info("ono: " + ono);
        OrderDTO dto = orderService.read(ono);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(Long ono , RedirectAttributes redirectAttributes) {
        log.info("ono: " + ono);
        orderService.remove(ono);
        redirectAttributes.addFlashAttribute("msg",ono) ;
        return "redirect:/order/list";

    }

    @PostMapping("/modify")
    public String modify(OrderDTO dto,@ModelAttribute("requestDTO") OrderPageRequestDTO requestDTO, RedirectAttributes redirectAttributes){
        log.info("post modify.........................................");
        log.info("dto: " + dto);
        orderService.modify(dto);
        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("ono",dto.getOno());
        return "redirect:/order/read";
    }

    @GetMapping("/popup")
    public void popup(ContractPageRequestDTO contractPageRequestDTO, Model model) {
        log.info("popup get...");
        log.info("(list2)contractPageRequestDTO : \" + contractPageRequestDTO");
        model.addAttribute("result", contractService.getList2(contractPageRequestDTO));
    }

    @GetMapping("/list2")
    public void list2(ContractPageRequestDTO contractPageRequestDTO, Model model){
        log.info("(list2)contractPageRequestDTO : \" + contractPageRequestDTO");
        model.addAttribute("result", contractService.getList2(contractPageRequestDTO));
    }

    @GetMapping("/chartmodal")
    @ResponseBody
    public ResponseEntity<List<OrderChartDTO>> chartmodal(String month, String orderstate){
        return new ResponseEntity<>(orderChartService.getList(month, orderstate), HttpStatus.OK);
    }

    public void ostatecount(Long count){

    }
}



