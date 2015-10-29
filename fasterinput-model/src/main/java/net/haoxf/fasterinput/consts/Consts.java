package net.haoxf.fasterinput.consts;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-10-25
 */
public class Consts {
    //token生成和解析使用的16位密钥
    public static final String SECRET_KEY = "testtesttesttest";

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
}
