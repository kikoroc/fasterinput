package net.haoxf.fasterinput;

import com.google.gson.Gson;
import net.haoxf.fasterinput.model.HttpRet;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2015/10/29 0029.
 */
public class WebUtils {
    public static void writeHttpRetToResponse(HttpServletResponse response, HttpRet ret) throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter()
                .write(gson.toJson(ret));
        response.getWriter().flush();
    }

    private static Gson gson = new Gson();
}
