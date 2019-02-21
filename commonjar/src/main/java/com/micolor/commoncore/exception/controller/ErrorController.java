package com.micolor.commoncore.exception.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Evangoe on 11/05/17.
 */
@Controller
@EnableAutoConfiguration
@ApiIgnore
public class ErrorController {

    @RequestMapping(value="/error/loginerror")
    public String loginError(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        return "/system/error/loginerror";
    }
}
