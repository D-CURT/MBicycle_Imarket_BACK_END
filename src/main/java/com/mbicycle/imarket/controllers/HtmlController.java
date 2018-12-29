package com.mbicycle.imarket.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HtmlController {

//    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
//    public String login2JspView() {
//        return "login";
//    }

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String indexHtmlView() {
        return "index";
    }

    @RequestMapping(value = {"/indexLogged"}, method = RequestMethod.GET)
    public String indexLoggedHtmlView() {
        return "index";
    }

}