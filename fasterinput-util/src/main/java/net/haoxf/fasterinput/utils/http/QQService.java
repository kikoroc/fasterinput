package net.haoxf.fasterinput.utils.http;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Administrator on 2015/11/17 0017.
 */
public interface QQService {

    /**
     * qq access_token校验
     * @param accessToken
     * @return
     */
    @FormUrlEncoded
    @POST("/oauth2.0/me")
    Call<String> validToken(@Field("access_token") String accessToken);
}
