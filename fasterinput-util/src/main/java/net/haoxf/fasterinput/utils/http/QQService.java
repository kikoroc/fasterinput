package net.haoxf.fasterinput.utils.http;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Administrator on 2015/11/17 0017.
 */
public interface QQService {

    /**
     * qq access_token校验
     * @param accessToken
     * @return
     */
    @GET("/oauth2.0/me")
    Call<String> validToken(@Query("access_token") String accessToken);

    @GET("/user/get_user_info")
    Call<String> getUserInfo(
            @Query("access_token") String accessToken,
            @Query("oauth_consumer_key") String appId,
            @Query("openid") String openId);
}
