package net.haoxf.fasterinput.consts;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-10-25
 */
public class Consts {
    //token生成和解析使用的16位密钥
    public static final String SECRET_KEY = "testtesttesttest";

    public enum ThirdAccount{
        QQ(1, "1104814839", "", "https://graph.qq.com/"),
        WECHAT(2, "", "", ""),
        WEIBO(3, "", "", "");

        private int code;
        private String appId;
        private String appKey;
        private String baseUrl;

        private ThirdAccount(int code, String appId, String appKey, String baseUrl) {
            this.code = code;
            this.appId = appId;
            this.appKey = appKey;
            this.baseUrl = baseUrl;
        }

        public int getCode() {
            return code;
        }

        public String getAppId() {
            return appId;
        }

        public String getAppKey() {
            return appKey;
        }

        public String getBaseUrl() {
            return baseUrl;
        }
    }

    //业务处理状态码
    public enum Code{
        SUCCESS(200, "success."),
        TOKEN_EXPIRED(400, "token expired."),
        PARAM_ILLEGAL(401, "parameter illegal."),
        NOT_FOUND(404, "resource not found."),
        UNKNOWN_ERROR(500, "unknown error."),
        THIRD_ACCOUNT_AUTH_ERROR(501, "第三方帐号授权验证失败，请确认参数后重试");

        private int code;
        private String description;
        private Code(int code, String description){
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    public static void main(String[] args) {
        System.out.println(ThirdAccount.valueOf("QQ").baseUrl);
    }
}
