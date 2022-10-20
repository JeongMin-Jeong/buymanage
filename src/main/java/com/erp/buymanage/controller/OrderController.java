package com.erp.buymanage.controller;

import com.erp.buymanage.dto.OrderDTO;
import com.erp.buymanage.dto.PageRequestDTO3;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.erp.buymanage.service.OrderService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.jar.Attributes;

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
    public void list(PageRequestDTO3 pageRequestDTO, Model model) {
        log.info("list................" + pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));
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
    public void read(long ono, @ModelAttribute("requestDTO") PageRequestDTO3 requestDTO, Model model) {

        log.info("ono: " + ono);
        OrderDTO dto = service.read(ono);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(long ono , RedirectAttributes redirectAttributes) {
        log.info("ono: " + ono);
        service.remove(ono);
        redirectAttributes.addFlashAttribute("msg",ono) ;
        return "redirect: /order/list";

    }

    @PostMapping("/modify")
    public String modify(OrderDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO3 requestDTO,
                         RedirectAttributes redirectAttributes){


        log.info("post modify.........................................");
        log.info("dto: " + dto);

        service.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("ono",dto.getOno());


        return "redirect:/order/read";

    }


}



