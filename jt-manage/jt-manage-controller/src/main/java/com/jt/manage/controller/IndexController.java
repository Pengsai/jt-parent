package com.jt.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value = "/page/{module}")
    public String index(@PathVariable String module){
        return module;
    }

    @RequestMapping(value = "/index")
    public String index2(){
        System.out.println("sss");
        return "index";
    }
}
