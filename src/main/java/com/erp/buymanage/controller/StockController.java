package com.erp.buymanage.controller;

import com.erp.buymanage.dto.OrderPageRequestDTO;
import com.erp.buymanage.dto.StockPageRequestDTO;
import com.erp.buymanage.dto.StockDTO;
import com.erp.buymanage.service.OrderService;
import com.erp.buymanage.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/stock")
@Log4j2
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final OrderService orderService;

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

    @GetMapping({"/read", "/modify"})
    public void read(long sno, @ModelAttribute("requestDTO") StockPageRequestDTO requestDTO, Model model) {

        log.info("(read)sno : " + sno);

        StockDTO dto = stockService.read(sno);

        model.addAttribute("dto", dto);
    }

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
}
