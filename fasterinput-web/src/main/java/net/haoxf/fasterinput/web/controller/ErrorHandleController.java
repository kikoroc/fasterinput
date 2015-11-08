package net.haoxf.fasterinput.web.controller;

import net.haoxf.fasterinput.consts.Consts;
import net.haoxf.fasterinput.model.HttpRet;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-11-8
 */
@RestController
public class ErrorHandleController implements ErrorController {

    @RequestMapping("/error")
    public HttpRet handlerError(){
        return new HttpRet(Consts.Code.NOT_FOUND.getCode(), "resouces not found.", null);
    }
    @Override
    public String getErrorPath() {
        return null;
    }
}
