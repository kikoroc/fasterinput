package net.haoxf.fasterinput.web.controller;

import net.haoxf.fasterinput.consts.Consts.Code;
import net.haoxf.fasterinput.exceptions.TokenException;
import net.haoxf.fasterinput.model.HttpRet;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-10-18
 */
@RestController
public class GlobalController extends BaseController implements ErrorController{

    @RequestMapping("/test")
    public HttpRet test() throws TokenException {
        throw new TokenException("test error");
    }

    @RequestMapping("/error")
    public HttpRet handlerError(){
        return new HttpRet(Code.NOT_FOUND.getCode(), "resouces not found.", null);
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
