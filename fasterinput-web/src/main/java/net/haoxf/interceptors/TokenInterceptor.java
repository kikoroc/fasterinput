package net.haoxf.interceptors;

import net.haoxf.utils.AuthUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    private Log log = LogFactory.getLog(TokenInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            log.error("token is null.");
            return false;
        }
        AuthUtil.AuthRet authUser = AuthUtil.parseToken(token);
        return true;
    }
}
