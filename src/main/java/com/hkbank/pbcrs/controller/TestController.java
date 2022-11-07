package com.hkbank.pbcrs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
    @RequestMapping("/test")
    @ResponseBody

    public void test(@RequestBody List<Map<String,String>> data, HttpServletRequest request){
        System.out.println(data);
    }
}

