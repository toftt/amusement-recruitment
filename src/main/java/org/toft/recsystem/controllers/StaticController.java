package org.toft.recsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StaticController {

    @RequestMapping(value = {"/", "/login", "/register"}, method = RequestMethod.GET)
    public String getFallback() {

        return "index.html";
    }
}