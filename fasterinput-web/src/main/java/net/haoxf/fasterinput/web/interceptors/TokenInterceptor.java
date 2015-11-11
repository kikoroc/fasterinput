package net.haoxf.fasterinput.web.interceptors;

import net.haoxf.fasterinput.exceptions.TokenException;
import net.haoxf.fasterinput.utils.AuthUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-10-25
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    private final long TOKEN_EXPIRED = 7*24*3600*1000; // one week.

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            throw new IllegalArgumentException("token cannot be null.");
        }
        AuthUtil.AuthRet authUser = AuthUtil.parseToken(token);
        if(System.currentTimeMillis() - authUser.getTimestamp() > TOKEN_EXPIRED){
            throw new TokenException("token expired.");
        }

        //将token解析到的uid写入request
        request.setAttribute("uid", authUser.getUid());
        return true;
    }
}
