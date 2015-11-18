package net.haoxf.fasterinput.web.controller;

import net.haoxf.fasterinput.consts.Consts.Code;
import net.haoxf.fasterinput.consts.Consts.ThirdAccount;
import net.haoxf.fasterinput.model.HttpRet;
import net.haoxf.fasterinput.utils.CommonUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/11/9 0009.
 */
@RestController
public class AccountController {

    @RequestMapping("/api/account/add")
    public HttpRet index(){
        return new HttpRet(Code.SUCCESS.getCode(), "welcome.", null);
    }

    @RequestMapping(value = "/api/register", method = RequestMethod.POST)
    public HttpRet register(
            @RequestParam("icon") String icon,  //用户头像url
            @RequestParam("userName") String userName,  //用户名称
            @RequestParam("gender") int gender, //用户性别
            @RequestParam("thirdAccountFrom") int thirdAccountFrom, //第三方帐号来源
            @RequestParam("thirdAccountId") String thirdAccountId,  //第三方帐号id
            @RequestParam("thirdAccessToken") String thirdAccessToken,  //第三方access token
            @RequestParam(value = "openId", required = false, defaultValue = "") String openId  //openid，微信open api会用到
    ){
        Assert.isTrue((!StringUtils.isEmpty(userName) && !CommonUtils.containIllegalCharacters(userName)), "用户名非法");
        Assert.isTrue(ThirdAccount.values.contains(thirdAccountFrom), "第三方账号来源非法");

    }

}
