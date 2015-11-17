package net.haoxf.fasterinput.utils;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Administrator on 2015/11/17 0017.
 */
public interface AccessTokenValidService {

    /**
     * qq access_token校验
     * @param accessToken
     * @return
     */
    @FormUrlEncoded
    @POST("/oauth2.0/me")
    Call<String> validQQToken(@Field("access_token") String accessToken);
}
