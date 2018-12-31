package com.mbicycle.imarket.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HtmlController {

//    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
//    public String login2JspView() {
//        return "index2";
//    }

    @GetMapping("/index")
    public String indexHtmlView() {
        return "index";
    }
}