package net.haoxf.fasterinput.controller;

import net.haoxf.fasterinput.model.HttpRet;
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
public class GlobalController {

    @RequestMapping("/api/account/add")
    public HttpRet index(){
        return new HttpRet(200, "welcome.", null);
    }

    @RequestMapping("/test")
    public HttpRet test(){
        return new HttpRet(200, "test", null);
    }
}
