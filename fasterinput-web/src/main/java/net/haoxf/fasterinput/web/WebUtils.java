package net.haoxf.fasterinput.web;

import com.google.gson.Gson;
import net.haoxf.fasterinput.model.HttpRet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/29 0029.
 */
public class WebUtils {

    private static Gson gson = new Gson();
    protected static Logger logger = LoggerFactory.getLogger(WebUtils.class);

    public static void writeHttpRetToResponse(HttpServletResponse response, HttpRet ret) {
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter()
                    .write(gson.toJson(ret));
            response.getWriter().flush();
        }catch(IOException e){
            logger.error("error@writeHttpRetToResponse", e);
        }
    }

    public static String requestMsgFormat(HttpServletRequest request){
        StringBuffer paramBuffer = new StringBuffer();
        Map<String, String[]> parameters = request.getParameterMap();
        Iterator<String> keySet = parameters.keySet().iterator();
        while (keySet.hasNext()){
            String key = keySet.next();
            String[] value = parameters.get(key);
            paramBuffer.append(key + "=");
            paramBuffer.append(Arrays.toString(value) + "&");
        }

        return paramBuffer.toString();
    }
}
