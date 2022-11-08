package com.erp.buymanage.controller;

import com.erp.buymanage.dto.*;
import com.erp.buymanage.entity.OrderChart;
import com.erp.buymanage.entity.OrderEntity;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @GetMapping({"/read","/modify","/list2"})
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

//    @GetMapping("/list2")
//    public void list2(ContractPageRequestDTO contractPageRequestDTO, Model model){
//        log.info("(list2)contractPageRequestDTO : \" + contractPageRequestDTO");
//        model.addAttribute("result", contractService.getList2(contractPageRequestDTO));
//    }

    @GetMapping("/chartmodal")
    @ResponseBody
    public ResponseEntity<List<OrderDTO>> chartmodal(String year, String month, String ocode ){

        String ym = year + "-" + month;

//        OrderDTO dto = orderChartService.read(ono);
//        model.addAttribute("dto", dto);
        return new ResponseEntity<>(orderChartService.getList2(ym, ocode), HttpStatus.OK);

    }
//@RequestMapping("/modalchart")
//
//    public @ResponseBody List<OrderEntity> count(String ostate , String orderdate) {
//        List<OrderEntity> sumSum = new ArrayList<>();
//        for (int i=0; i<22; i+=7) {
//            sumSum.add(0, orderChartService.countbyostateandorderdate(String ostate , String orderdate),i );
//
//            return sumSum;
//        }
//}
//    public void ostatecount(Long count){
//
//    }
//@GetMapping("/monthly-sales/{year}")
//public List<OrderEntity> count(@PathVariable("year") String year){
//    return orderChartService.get(year);
//}
@GetMapping("/chartRegister")
public String chartRegister(RedirectAttributes redirectAttributes) {

    orderChartService.chartRegister();

    redirectAttributes.addFlashAttribute("msg", 1);

    return "redirect:/order/list";
}

//    @RequestMapping("/count")
//    @ResponseBody
//    public  ResponseEntity<List<OrderEntity>> ordercount(Model model, OrderEntity order)  {
//        List<OrderEntity> countlist = orderChartService.ordercount(order);
//        model.addAttribute("countlist", countlist);
////        return new ResponseEntity<>(orderChartService.ordercount(order), HttpStatus.OK);
//        return countlist;
//    }

    @GetMapping("/modalread2")
    @ResponseBody
    public OrderDTO modalread2(@RequestParam Map<String, Object> param) {

        System.out.println("컨트롤러 ono : " + param.get("ono"));

        String str = (String)param.get("ono");

        long ono = Long.parseLong(str);

        OrderDTO dto = orderService.read(ono);

        return dto;
    }

}



