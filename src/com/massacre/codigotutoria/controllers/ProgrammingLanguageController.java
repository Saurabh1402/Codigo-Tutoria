package com.massacre.codigotutoria.controllers;

import com.massacre.codigotutoria.models.ProgrammingLanguage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by saurabh on 14/7/17.
 */
@Controller
@RequestMapping(value="/codigotutoria")
public class ProgrammingLanguageController {
    ProgrammingLanguage programmingLanguage=new ProgrammingLanguage();
    @RequestMapping(value="/getIndexJSON",method= RequestMethod.GET,produces = "application/json")
    public @ResponseBody ProgrammingLanguage helloPage(ModelMap map){
        programmingLanguage.setTitle("Core Java");
        programmingLanguage.setColorAccent("color-accent");
        programmingLanguage.setColorPrimary("color-primary");
        programmingLanguage.setColorPrimaryDark("color-primary-dark");
        programmingLanguage.setImageResource("image-resource");
        return programmingLanguage;
    }
}
