package net.haoxf.fasterinput.web.controller;

import net.haoxf.fasterinput.web.WebUtils;
import net.haoxf.fasterinput.consts.Consts.Code;
import net.haoxf.fasterinput.exceptions.TokenException;
import net.haoxf.fasterinput.model.HttpRet;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-10-29
 */
public class BaseController {

    @ExceptionHandler
    public void exception(HttpServletResponse response,
                          Exception ex) throws IOException{
        HttpRet ret = new HttpRet();
        if(ex instanceof IllegalArgumentException){
            ret.setCode(Code.PARAM_ILLEGAL.getCode());
            ret.setMsg(ex.getMessage());
        }else if(ex instanceof TokenException) {
            ret.setCode(Code.UNKNOWN_ERROR.getCode());
            ret.setMsg(ex.getMessage());
        }else if(ex instanceof NoHandlerFoundException){
            ret.setCode(Code.NOT_FOUND.getCode());
            ret.setMsg(Code.NOT_FOUND.getDescription());
        }else{
            ret.setCode(Code.UNKNOWN_ERROR.getCode());
            ret.setMsg(ex.getMessage());
        }
        WebUtils.writeHttpRetToResponse(response, ret);
    }
}
