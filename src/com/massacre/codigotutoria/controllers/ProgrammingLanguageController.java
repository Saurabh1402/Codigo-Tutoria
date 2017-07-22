package com.massacre.codigotutoria.controllers;

import com.massacre.codigotutoria.dbhelper.DatabaseEngine;
import com.massacre.codigotutoria.models.LanguageHeader;
import com.massacre.codigotutoria.models.ProgrammingLanguage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by saurabh on 14/7/17.
 */
@RestController
@RequestMapping(value="/codigotutoria",produces = "application/json")
public class ProgrammingLanguageController {
    private ProgrammingLanguage programmingLanguage;

    @RequestMapping(value="/getIndexJSON",method= RequestMethod.GET,produces = "application/json")
    public ProgrammingLanguage helloPage(ModelMap map){
//        ApplicationContext ctx=new ClassPathXmlApplicationContext("//WEB-INF/applicationContext.xml");
        GenericXmlApplicationContext ctx=new GenericXmlApplicationContext();
        ctx.load("WEB-INF/applicationContext.xml");
        ctx.refresh();
        DatabaseEngine databaseEngine=ctx.getBean("dbEngineDaoBean", DatabaseEngine.class);
        programmingLanguage=databaseEngine.findLanguageByLanguageId(3l);
        return programmingLanguage;
    }
}
