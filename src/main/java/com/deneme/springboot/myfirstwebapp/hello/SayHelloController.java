package com.deneme.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello() {
        return "Hello What Are You Learning Today";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml() {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>My First Html Page</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("My First Html Page With Body");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
    @RequestMapping("say-hello-jsp")
    //@ResponseBody
    //Response Body annotation doesnt use with jsp file
    //JSP file have spesicif folder /src/main/resources/META-INF/resources/WEB-INF/jsp
    //JSP java server pages HTML kodlarını vey agörüntü kodları için var
    ///src/main/resources/META-INF/resources/WEB-INF/jsp/SayHello.jsp
    public String sayHelloJsp() {
        //JSP File name
        return "SayHello";
    }

}


