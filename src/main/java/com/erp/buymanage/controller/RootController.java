package com.erp.buymanage.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Log4j2
@RequestMapping(value = "/", method = RequestMethod.GET)
public class RootController {
    public String index(){
        return "index";
    }
}
