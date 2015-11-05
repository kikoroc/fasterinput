package net.haoxf.fasterinput.web.controller;

import net.haoxf.fasterinput.exceptions.TokenException;
import net.haoxf.fasterinput.model.HttpRet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-10-18
 */
@RestController
public class GlobalController extends BaseController{

    @RequestMapping("/api/account/add")
    public HttpRet index(){
        return new HttpRet(200, "welcome.", null);
    }

    @RequestMapping("/test")
    public HttpRet test() throws TokenException {
        throw new TokenException("test error");
    }
}
