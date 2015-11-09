package net.haoxf.fasterinput.web.controller;

import net.haoxf.fasterinput.consts.Consts;
import net.haoxf.fasterinput.model.HttpRet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/11/9 0009.
 */
@RestController
public class AccountController extends BaseController {

    @RequestMapping("/api/account/add")
    public HttpRet index(){
        return new HttpRet(Consts.Code.SUCCESS.getCode(), "welcome.", null);
    }

}
