package com.erp.buymanage.controller;

import com.erp.buymanage.dto.ProductImageDTO;
import com.erp.buymanage.dto.ProductPageRequestDTO;
import com.erp.buymanage.dto.ProductDTO;
import com.erp.buymanage.security.dto.AuthMemberDTO;
import com.erp.buymanage.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/product")
@Log4j2
@RequiredArgsConstructor // 자동 주입을 위한 어노테이션
public class ProductController {

    private final ProductService service;

    @GetMapping("/")
    public String index(){
        log.info(">>>>> ProductController (index)");

        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public void list(ProductPageRequestDTO productPageRequestDTO, Model model, @AuthenticationPrincipal AuthMemberDTO authMember){
        log.info(">>>>> ProductController (list)");

        model.addAttribute("result", service.getList(productPageRequestDTO));
    }

    @GetMapping("/register")
    public void register(@AuthenticationPrincipal AuthMemberDTO authMember){
        log.info(">>>>> ProductController (register GetMapping)");
    }

    @PostMapping("/register")
    public String register(ProductDTO dto, RedirectAttributes redirectAttributes){
        log.info(">>>>> ProductController (register PostMapping)");

        service.register(dto);
        redirectAttributes.addFlashAttribute("pno", dto.getPno());
        return "redirect:/product/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(long pno, @ModelAttribute("requestDTO") ProductPageRequestDTO productPageRequestDTO, Model model){
        log.info(">>>>> ProductController (read,modify GetMapping)");

        ProductDTO dto = service.read(pno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String modify(ProductDTO dto, @ModelAttribute("requestDTO") ProductPageRequestDTO productPageRequestDTO, RedirectAttributes redirectAttributes){
        log.info(">>>>> ProductController (modify PostMapping)");

        List<ProductImageDTO> imageDTOList = service.read(dto.getPno()).getImageDTOList();

        ProductDTO newDto = new ProductDTO();
        newDto.setPcode(dto.getPcode());
        newDto.setPname(dto.getPname());
        newDto.setPtype1(dto.getPtype1());
        newDto.setPtype2(dto.getPtype2());
        newDto.setPcontent(dto.getPcontent());
        newDto.setPetc(dto.getPetc());
        newDto.setImageDTOList(imageDTOList);
        if(dto.getImageDTOList().size() != 0){
            newDto.setImageDTOList(dto.getImageDTOList());
        }

        service.remove(dto.getPno());
        service.register(newDto);

//        redirectAttributes.addAttribute("page", productPageRequestDTO.getPage());
//        redirectAttributes.addAttribute("pno", newDtoPno);
        return "redirect:/product/list";
    }

    @PostMapping("/remove")
    public String remove(long pno, RedirectAttributes redirectAttributes){
        log.info(">>>>> ProductController (remove PostMapping)");

        service.remove(pno);
        redirectAttributes.addFlashAttribute("pno", pno);
        return "redirect:/product/list";
    }
}
