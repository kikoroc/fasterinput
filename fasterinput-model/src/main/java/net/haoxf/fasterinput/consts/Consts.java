package net.haoxf.fasterinput.consts;

import java.util.HashSet;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-10-25
 */
public class Consts {
    //token生成和解析使用的16位密钥
    public static final String SECRET_KEY = "testtesttesttest";

    public enum ThirdAccount{
        QQ(1, "", "", ""),
        WECHAT(2, "", "", ""),
        WEIBO(3, "", "", "");

        private int accountCode;
        private String appId;
        private String appKey;
        private String baseUrl;
        public static HashSet<Integer> values = new HashSet<>();

        ThirdAccount(int accountCode, String appId, String appKey, String baseUrl) {
            this.accountCode = accountCode;
            this.appId = appId;
            this.appKey = appKey;
            this.baseUrl = baseUrl;
        }

        static{
            values = new HashSet<>();
            for(ThirdAccount ta:ThirdAccount.values()){
                values.add(ta.getAccountCode());
            }
        }

        public int getAccountCode() {
            return accountCode;
        }

        public void setAccountCode(int accountCode) {
            this.accountCode = accountCode;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppKey() {
            return appKey;
        }

        public void setAppKey(String appKey) {
            this.appKey = appKey;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }
    }

    //业务处理状态码
    public enum Code{
        SUCCESS(200, "success."),
        TOKEN_EXPIRED(400, "token expired."),
        PARAM_ILLEGAL(401, "parameter illegal."),
        NOT_FOUND(404, "resource not found."),
        UNKNOWN_ERROR(500, "unknown error.");

        private int code;
        private String description;
        private Code(int code, String description){
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static void main(String[] args) {
        System.out.println(ThirdAccount.values());
    }
}
