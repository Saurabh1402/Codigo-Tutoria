package com.massacre.codigotutoria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

/**
 * Created by saurabh on 15/7/17.
 */
@Controller
@RequestMapping(value="/")
public class Redirector {
    @RequestMapping(method = RequestMethod.GET)
    public String redirect(){
        return "redirect:/codigotutoria/getIndexJSON/";
    }
}
