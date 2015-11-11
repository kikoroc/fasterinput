package net.haoxf.fasterinput.web.controller;

import net.haoxf.fasterinput.consts.Consts.Code;
import net.haoxf.fasterinput.exceptions.TokenException;
import net.haoxf.fasterinput.model.HttpRet;
import net.haoxf.fasterinput.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-10-29
 */
@ControllerAdvice
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler
    public void exception(HttpServletRequest request,
                          HttpServletResponse response,
                          Exception ex) {
        logger.error("ExceptionHandler->", ex);
        logger.error(String.format("request parameters-> %s", WebUtils.requestMsgFormat(request)));

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
