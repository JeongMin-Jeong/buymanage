package com.erp.buymanage.controller;

import com.erp.buymanage.dto.OrderDTO;
import com.erp.buymanage.dto.OrderPageRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.erp.buymanage.service.OrderService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
@Log4j2

public class OrderController {

    private final OrderService service;

    @GetMapping("/")
    public String index() {
        return "redirect:/order/list";
    }

    @GetMapping("/list")
    public void list(OrderPageRequestDTO orderPageRequestDTO, Model model) {
     log.info("list................" + orderPageRequestDTO);
        model.addAttribute("result", service.getList(orderPageRequestDTO));
    }

    @GetMapping("/register")
    public void register() {
        log.info("register get...");
    }

    @PostMapping("/register")
    public String registerPost(OrderDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto..." + dto);
        //새로 추가된 엔티티의 번호
        long ono = service.register(dto);

        redirectAttributes.addFlashAttribute("msg",ono);

        return "redirect:/order/list";
    }

    @GetMapping({"/read","/modify"})
    public void read(long ono, @ModelAttribute("requestDTO") OrderPageRequestDTO requestDTO, Model model) {
        log.info("ono: " + ono);
        OrderDTO dto = service.read(ono);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(Long ono , RedirectAttributes redirectAttributes) {
        log.info("ono: " + ono);
        service.remove(ono);
        redirectAttributes.addFlashAttribute("msg",ono) ;
        return "redirect:/order/list";

    }

    @PostMapping("/modify")
    public String modify(OrderDTO dto,@ModelAttribute("requestDTO") OrderPageRequestDTO requestDTO, RedirectAttributes redirectAttributes){
        log.info("post modify.........................................");
        log.info("dto: " + dto);

        service.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("ono",dto.getOno());


        return "redirect:/order/read";

    }


}



