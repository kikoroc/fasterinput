package net.haoxf.fasterinput.web.controller;

import net.haoxf.fasterinput.consts.Consts.Code;
import net.haoxf.fasterinput.consts.Consts.ThirdAccount;
import net.haoxf.fasterinput.model.Account;
import net.haoxf.fasterinput.model.HttpRet;
import net.haoxf.fasterinput.service.AccountService;
import net.haoxf.fasterinput.utils.AuthUtil;
import net.haoxf.fasterinput.utils.http.AccessTokenValidServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by Administrator on 2015/11/9 0009.
 */
@RestController
public class AccountController {

    @Autowired
    @Qualifier("accountService")
    private AccountService accountService;

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public HttpRet login(
            @RequestParam("thirdAccountFrom") String thirdAccountFrom, //第三方帐号来源
            @RequestParam("thirdAccessToken") String thirdAccessToken  //第三方access token
    ) throws IOException {
        ThirdAccount thirdAccount = ThirdAccount.valueOf(thirdAccountFrom.toUpperCase());
        Assert.notNull(thirdAccount, String.format("第三方账号来源非法 from:%s", thirdAccountFrom));
        //解析qq token得到openid
        String openId = AccessTokenValidServiceImpl.parseQQToken(thirdAccessToken);
        //检查该Openid是否已经注册帐号
        Account account = accountService.queryAccount(thirdAccount.getCode(), openId);
        if(account != null){
            //如果已经注册，返回帐号信息+我们的token
            String token = AuthUtil.generateToken(account.getId(), System.currentTimeMillis());
            account.setToken(token);
            return new HttpRet(Code.SUCCESS.getCode(), "login success", account);
        }else{
            //没有没有注册，自动注册为我们的帐号并返回帐号信息+我们的token
            AccessTokenValidServiceImpl.QQUserInfo qqUserInfo =
                    AccessTokenValidServiceImpl.getQQUserInfo(thirdAccessToken, thirdAccount.getAppId(), openId);

            Account newAccount = new Account();
            newAccount.setAccountFrom(thirdAccount.getCode());
            newAccount.setAvatarUrl(
                    //@see http://wiki.connect.qq.com/get_user_info
                    StringUtils.isEmpty(qqUserInfo.getFigureurl_qq_2())?
                            qqUserInfo.getFigureurl_qq_1():qqUserInfo.getFigureurl_qq_2()
            );
            newAccount.setGender(qqUserInfo.getGender().equals("男") ? 1 : 0);
            newAccount.setNickName(qqUserInfo.getNickname());
            newAccount.setOpenId(openId);

            accountService.addAccount(newAccount);

            String token = AuthUtil.generateToken(newAccount.getId(), System.currentTimeMillis());
            newAccount.setToken(token);

            return new HttpRet(Code.SUCCESS.getCode(), "auto register success", newAccount);
        }
    }

}
