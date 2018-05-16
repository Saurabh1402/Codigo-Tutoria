package com.massacre.codigotutoria.controllers;

import com.google.gson.JsonArray;
import com.massacre.codigotutoria.dbhelper.DatabaseEngine;
import com.massacre.codigotutoria.models.LanguageHeader;
import com.massacre.codigotutoria.models.ProgrammingContainer;
import com.massacre.codigotutoria.models.ProgrammingLanguage;
import org.apache.commons.logging.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh on 14/7/17.
 */
@RestController
@RequestMapping(value="/getindex")
public class ProgrammingLanguageController {

    @RequestMapping(value="/getindexforall",method= RequestMethod.GET,produces = "application/json")
    public List<ProgrammingLanguage> getFullIndex(String jsonArrayRequest){
        System.out.println("inside /getindexforall controller:");
        //        ApplicationContext ctx=new ClassPathXmlApplicationContext("//WEB-INF/applicationContext.xml");
        GenericXmlApplicationContext ctx=new GenericXmlApplicationContext();
        ctx.load("WEB-INF/applicationContext.xml");
        ctx.refresh();
        DatabaseEngine databaseEngine=ctx.getBean("dbEngineDaoBean", DatabaseEngine.class);
        List<ProgrammingLanguage> programmingLanguages=databaseEngine.findAllLanguage();
        return programmingLanguages;
    }

    @RequestMapping(value="/getindexforallExcluding",method= RequestMethod.POST,produces = "application/json")
    public List<ProgrammingLanguage> getExcludedIndex(@RequestBody List<ProgrammingContainer> containers){
        System.out.println("inside /getExcludedIndex     controller");
        GenericXmlApplicationContext ctx=new GenericXmlApplicationContext();
        ctx.load("WEB-INF/applicationContext.xml");
        ctx.refresh();
        DatabaseEngine databaseEngine=ctx.getBean("dbEngineDaoBean", DatabaseEngine.class);

        //        ApplicationContext ctx=new ClassPathXmlApplicationContext("//WEB-INF/applicationContext.xml");

//        Getting List of Language in ProgrammingContainer.class in the SQLite android database from String
//        List<ProgrammingContainer> containerList=null;
//        try {
//            containerList=ProgrammingContainer.getListFromJson(jsonArray);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Got the above List as containerList

//        finding new Languages as List<ProgrammingLanguage> excluding the containerList
        List<ProgrammingLanguage> programmingLanguages;
        if(containers.size()>0) {
            System.out.println("ProgrammingContainer:json :" + containers.get(0).toString());
            programmingLanguages = databaseEngine.findExcludedLanguage(containers);
        }else{
            System.out.println("ProgrammingContainer request is Empty: "+containers.size());
            System.out.println("finding new Language as List<ProgrammingLanguage> excluding the containerList");
            programmingLanguages = databaseEngine.findAllLanguage();
        }
        return programmingLanguages;
    }
}
