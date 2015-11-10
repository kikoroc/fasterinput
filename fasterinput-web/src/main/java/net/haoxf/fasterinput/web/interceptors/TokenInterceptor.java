package net.haoxf.fasterinput.web.interceptors;

import net.haoxf.fasterinput.consts.Consts.Code;
import net.haoxf.fasterinput.model.HttpRet;
import net.haoxf.fasterinput.utils.AuthUtil;
import net.haoxf.fasterinput.web.WebUtils;
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

    private final long TOKEN_EXPIRED = 7*24*3600*1000; // one week.

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            log.error("token is null.");
            HttpRet ret = new HttpRet();
            ret.setCode(Code.PARAM_ILLEGAL.getCode());
            ret.setMsg("token cannot be null");
            WebUtils.writeHttpRetToResponse(response, ret);
            return false;
        }
        AuthUtil.AuthRet authUser = AuthUtil.parseToken(token);
        if(System.currentTimeMillis() - authUser.getTimestamp() > TOKEN_EXPIRED){
            log.error("token expired.");
            HttpRet ret = new HttpRet();
            ret.setCode(Code.TOKEN_EXPIRED.getCode());
            ret.setMsg(Code.TOKEN_EXPIRED.getDescription());
            WebUtils.writeHttpRetToResponse(response, ret);
            return false;
        }

        //将token解析到的uid写入request
        request.setAttribute("uid", authUser.getUid());
        return true;
    }
}
