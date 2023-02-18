package com.deneme.springboot.myfirstwebapp.login;

import ch.qos.logback.core.model.Model;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("name")
public class WelcomeController {
    //@Autowired anotation doesnt recommended.Constructor injection !!
//    private AuthenticationService authenticationService;
//
//    public LoginController(AuthenticationService authenticationService) {
//        this.authenticationService = authenticationService;
//    }

    //For logger .
   // private Logger logger= LoggerFactory.getLogger(getClass());
    @RequestMapping(value = "/",method = RequestMethod.GET)
    //@RequestParam String name sayesinde url den gelen login?name=Meriç ismindeki Meriç parametresini alabiliyorum
    //ModelMap ten nesne oluşturarak jsp ye aldığım parametreyi yansıtabiliyorum.Put metodunun 1. parametresi ile jsp dosyasındaki isim aynı olmalı!
    /*@RequestParam String name, ModelMap modelMap*/
    public String login(ModelMap model) {
      //  modelMap.put("name",name);
        //System.out.println(name); NOT RECOOMMENDED FOR PROD CODE
        //logger is recommended but if I want to see logging level must be debug for this example
        //logger.debug("Request Param is :{}",name);
        model.put("name", "HardCode");
        //JSP File name
        return "welcome";
    }

//
//    @RequestMapping(value = "login",method = RequestMethod.POST)
//    public String gotoWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap modelMap){
//        if (authenticationService.authentication(name,password)) {
//            modelMap.put("name", name);
//            modelMap.put("password", password);
//
//            return "welcome";
//        }
//        modelMap.put("errorMessage","Invalid name or password!");
//        return "login";
//    }
}
