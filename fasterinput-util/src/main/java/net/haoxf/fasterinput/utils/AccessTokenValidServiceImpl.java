package net.haoxf.fasterinput.utils;

import net.haoxf.fasterinput.consts.Consts;
import retrofit.Retrofit;

/**
 * Created by Administrator on 2015/11/18 0018.
 */
public class AccessTokenValidServiceImpl {
    private static Retrofit qqTokenService;
    private static Retrofit wechatTokenService;
    private static Retrofit weiboTokenService;
    private static AccessTokenValidService qqService;
    private static AccessTokenValidService wechatService;
    private static AccessTokenValidService weiboService;

    static{
        qqTokenService = new Retrofit.Builder()
                .baseUrl(Consts.ThirdAccount.QQ.getBaseUrl())
                .build();
        wechatTokenService = new Retrofit.Builder()
                .baseUrl(Consts.ThirdAccount.WECHAT.getBaseUrl())
                .build();
        weiboTokenService = new Retrofit.Builder()
                .baseUrl(Consts.ThirdAccount.WEIBO.getBaseUrl())
                .build();
        qqService = qqTokenService.create(AccessTokenValidService.class);
        wechatService = wechatTokenService.create(AccessTokenValidService.class);
    }

    public static void main(String[] args) {
        qqService.validQQToken("");
    }
}
