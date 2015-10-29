package net.haoxf.fasterinput.interceptors;

import com.google.gson.Gson;
import net.haoxf.fasterinput.model.HttpRet;
import net.haoxf.fasterinput.utils.AuthUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-10-25
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {
    private Log log = LogFactory.getLog(TokenInterceptor.class);

    private final long TOKEN_EXPIRED = 7*24*3600*1000; // one week.
    private Gson gson = new Gson();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            log.error("token is null.");
        }
        AuthUtil.AuthRet authUser = AuthUtil.parseToken(token);
        if(System.currentTimeMillis() - authUser.getTimestamp() > TOKEN_EXPIRED){
            log.error("token expired.");
        }


        return true;
    }
}
