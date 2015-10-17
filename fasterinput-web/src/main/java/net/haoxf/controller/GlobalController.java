package net.haoxf.controller;

import net.haoxf.model.HttpRet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-10-18
 */
@RestController
@EnableAutoConfiguration
public class GlobalController {

    @RequestMapping("/")
    public HttpRet index(){
        return new HttpRet(200, "welcome.", null);
    }

    public static void main(String[] args){
        SpringApplication.run(GlobalController.class, args);
    }
}
