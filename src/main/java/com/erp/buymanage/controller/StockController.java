package com.erp.buymanage.controller;

import com.erp.buymanage.dto.*;
import com.erp.buymanage.service.HistoryService;
import com.erp.buymanage.service.OrderService;
import com.erp.buymanage.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/stock")
@Log4j2
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final OrderService orderService;
    private final HistoryService historyService;

    @GetMapping("/")
    public String index(){
        return "redirect:/stock/list";
    }


    @GetMapping("/list")
    public void list(StockPageRequestDTO stockPageRequestDTO, Model model){

        log.info("(list)stockPageRequestDTO : " + stockPageRequestDTO);

        model.addAttribute("result", stockService.getList(stockPageRequestDTO));
    }

    @GetMapping("/list2")
    public void list2(OrderPageRequestDTO orderPageRequestDTO, Model model) {

        log.info("(list2)orderPageRequestDTO : " + orderPageRequestDTO);

        model.addAttribute("result", orderService.getList2(orderPageRequestDTO));
    }

    @GetMapping("/register")
    public void register() {
        log.info("register get.....");
    }

    @PostMapping("/register")
    public String registerPost(StockDTO dto, RedirectAttributes redirectAttributes) {

        log.info("(register)dto : " + dto);

        //새로 추가된 엔티티의 번호
        Long sno = stockService.register(dto);

        redirectAttributes.addFlashAttribute("msg", sno);

        return "redirect:/stock/list";
    }

    @PostMapping("/inputregister")
    public String inputRegister(StockDTO dto) {

        log.info("(inputRegister) dto : " + dto);

        long sno = stockService.register(dto);
        OrderDTO orderDTO = new OrderDTO();
        long ono = Long.parseLong(dto.getSnote());
        orderDTO.setOno(ono);
        orderDTO.setOstate("입고처리");
        orderService.inputModify(orderDTO);

        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setSno(sno);
        System.out.println("HistoryDTO ============================== " +sno);
        historyDTO.setText(dto.getSin() + "개 입고");
        historyDTO.setRequester(dto.getRequester());
        System.out.println("HistoryDTO ============================== " +dto.getRequester());
        historyService.register(historyDTO);

        return "redirect:/stock/list2";
    }


    @GetMapping({"/read", "/modify"})
    public void read(long sno, @ModelAttribute("requestDTO") StockPageRequestDTO requestDTO, Model model) {

        log.info("(read)sno : " + sno);

        StockDTO dto = stockService.read(sno);

        //List<HistoryDTO> historyDTOList = historyService.getList(sno);

        model.addAttribute("dto", dto);
        model.addAttribute("result", historyService.getList(sno));
    }

    @GetMapping("/modalread")
    @ResponseBody
    public OrderDTO modalread(@RequestParam Map<String, Object> param) {

        System.out.println("컨트롤러 ono : " + param.get("ono"));

        String str = (String)param.get("ono");

        long ono = Long.parseLong(str);

        OrderDTO dto = orderService.read(ono);

        return dto;
    }

    @GetMapping("/modalread2")
    @ResponseBody
    public StockDTO modalread2(@RequestParam Map<String, Object> param) {

        System.out.println("컨트롤러 sno : " + param.get("sno"));

        String str = (String)param.get("sno");

        long sno = Long.parseLong(str);

        StockDTO dto = stockService.read(sno);

        return dto;
    }

    @Transactional
    @PostMapping("/remove")
    public String remove(Long sno, RedirectAttributes redirectAttributes) {

        log.info("(remove)sno : " + sno);

        stockService.remove(sno);

        redirectAttributes.addFlashAttribute("msg", sno);

        return "redirect:/stock/list";
    }

    @PostMapping("/modify")
    public String modify(StockDTO dto, @ModelAttribute("requestDTO") StockPageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {

        log.info("(modify) dto : " + dto);

        stockService.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type1", requestDTO.getType1());
        redirectAttributes.addAttribute("type2", requestDTO.getType2());
        redirectAttributes.addAttribute("type3", requestDTO.getType3());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("sno", dto.getSno());

        return "redirect:/stock/read";
    }

    @PostMapping("/outModify")
    public String outModify(StockDTO dto){

        System.out.println("outModift 확인 데이터 =======================================" + dto.getSno());
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setSno(dto.getSno());
        historyDTO.setText(dto.getSout() + "개 출고");
        historyDTO.setRequester(dto.getRequester());
        System.out.println("HistoryDTO ============================== " +dto.getRequester());
        historyService.register(historyDTO);
        stockService.outModify(dto);

        return "redirect:/stock/list";
    }
}
