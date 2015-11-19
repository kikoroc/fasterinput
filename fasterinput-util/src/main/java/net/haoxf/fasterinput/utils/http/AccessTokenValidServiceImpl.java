package net.haoxf.fasterinput.utils.http;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import net.haoxf.fasterinput.consts.Consts;
import net.haoxf.fasterinput.exceptions.ThirdAccessTokenIllegalException;
import org.apache.commons.lang3.StringUtils;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import java.io.IOException;

/**
 * Created by Administrator on 2015/11/18 0018.
 */
public class AccessTokenValidServiceImpl {

    private static Gson gson = new Gson();

    private static Retrofit qqTokenService = new Retrofit.Builder()
            .baseUrl(Consts.ThirdAccount.QQ.getBaseUrl())
            .addConverterFactory(StringConverterFactory.create()) //qq的api返回结果一会儿是jsonp一会儿是json,这里直接按string处理，业务再根据需求处理。
            .build();
    private static QQService qqService = qqTokenService.create(QQService.class);

    public static String parseQQToken(String token) throws IOException {
        Call<String> call = qqService.validToken(token);
        Response<String> resp = call.execute();
        String jsonp = resp.body();
        String jsonStr = jsonp.substring(jsonp.indexOf("(")+1, jsonp.lastIndexOf(")")).trim();
        QQTokenSuccess success = gson.fromJson(jsonStr, QQTokenSuccess.class);
        if(success.getOpenid() == null || success.getClient_id() == null){
            throw new ThirdAccessTokenIllegalException(Consts.ThirdAccount.QQ, token, "第三方帐号token非法");
        }
        return success.getOpenid();
    }

    public static QQUserInfo getQQUserInfo(String accessToken, String appId, String openId) throws IOException {
        Call<String> call = qqService.getUserInfo(accessToken, appId, openId);
        Response<String> resp = call.execute();
        QQUserInfo qqUserInfo = gson.fromJson(resp.body(), QQUserInfo.class);
        if(qqUserInfo.getRet()!=0){
            throw new ThirdAccessTokenIllegalException(Consts.ThirdAccount.QQ, gson.toJson(qqUserInfo), "通过token获取qq帐号信息失败");
        }

        return qqUserInfo;
    }

    public static void main(String[] args) throws IOException {
        String token = "690DFA9AF690256CDD690BA6094BADB1";
        String openId = parseQQToken(token);
        System.out.println(openId);
        QQUserInfo qqUserInfo = getQQUserInfo(token, Consts.ThirdAccount.QQ.getAppId(),
                openId);
        System.out.println(gson.toJson(qqUserInfo));
    }

    private static class QQTokenSuccess{
        private String client_id;
        private String openid;

        private QQTokenSuccess(String client_id, String openid) {
            this.client_id = client_id;
            this.openid = openid;
        }

        public String getClient_id() {
            return client_id;
        }

        public void setClient_id(String client_id) {
            this.client_id = client_id;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }
    }

    public static class QQUserInfo{
        private int ret;
        private String msg;
        private String nickname;
        private String gender;
        private String figureurl_qq_1;
        private String figureurl_qq_2;

        public QQUserInfo() {
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getFigureurl_qq_1() {
            return figureurl_qq_1;
        }

        public void setFigureurl_qq_1(String figureurl_qq_1) {
            this.figureurl_qq_1 = figureurl_qq_1;
        }

        public String getFigureurl_qq_2() {
            return figureurl_qq_2;
        }

        public void setFigureurl_qq_2(String figureurl_qq_2) {
            this.figureurl_qq_2 = figureurl_qq_2;
        }

        public int getRet() {
            return ret;
        }

        public void setRet(int ret) {
            this.ret = ret;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}
