package com.hang.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 前台默认类
 */
@Controller
@RequestMapping("/admin")
public class IndexController {

    @GetMapping(value = "/")
    public String index(ModelMap map, HttpServletResponse response) throws Exception{
        map.addAttribute("Model", "Model");

        response.sendRedirect("/admin/login");

        return "index/index/index";
    }

}
