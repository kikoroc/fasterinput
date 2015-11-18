package net.haoxf.fasterinput.utils.http;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import net.haoxf.fasterinput.consts.Consts;
import net.haoxf.fasterinput.exceptions.ThirdAccessTokenIllegalException;
import org.apache.commons.lang3.StringUtils;
import retrofit.Call;
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
            .addConverterFactory(new StringConverterFactory())
            .build();
    private static QQService qqService = qqTokenService.create(QQService.class);

    public static QQTokenSuccess parseQQToken(String token) throws IOException{
        Call<String> call = qqService.validToken(token);
        Response<String> resp = call.execute();
        String jsonp = resp.body();
        String jsonStr = jsonp.substring(jsonp.indexOf("(")+1, jsonp.lastIndexOf(")")).trim();
        QQTokenSuccess success = gson.fromJson(jsonStr, QQTokenSuccess.class);
        if(success.getOpenid() == null || success.getClient_id() == null){
            throw new ThirdAccessTokenIllegalException(Consts.ThirdAccount.QQ.getAccountCode(), token, "第三方帐号token非法");
        }
        if(!StringUtils.equals(success.getClient_id(), Consts.ThirdAccount.QQ.getAppId())){
            throw new ThirdAccessTokenIllegalException(Consts.ThirdAccount.QQ.getAccountCode(), token, "第三方帐号token解析得到的appid非法");
        }
        return success;
    }

    public static void main(String[] args) throws IOException {
        Call<String> call = qqService.validToken("test");
        Response<String> resp = call.execute();
        String jsonp = resp.body();
        String jsonStr = jsonp.substring(jsonp.indexOf("(")+1, jsonp.lastIndexOf(")")).trim();
        QQTokenSuccess success = gson.fromJson(jsonStr, QQTokenSuccess.class);
        System.out.println(success.getClient_id()+","+success.getOpenid());
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

}
