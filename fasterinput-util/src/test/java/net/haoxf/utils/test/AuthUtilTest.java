package net.haoxf.utils.test;


import junit.framework.TestCase;
import net.haoxf.utils.AuthUtil;
import org.junit.Assert;

import java.util.Date;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-10-25
 */
public class AuthUtilTest extends TestCase{

    public void testAuth(){
        long uid = 123456l;
        long timestamp = new Date().getTime();

        try {
            long st = System.currentTimeMillis();
            String token = AuthUtil.generateToken(uid, timestamp);
            long et = System.currentTimeMillis();
            System.out.println("generate token:"+token+",cost:"+(et-st)+" ms.");
            st = System.currentTimeMillis();
            AuthUtil.AuthRet ret = AuthUtil.parseToken(token);
            et = System.currentTimeMillis();
            System.out.println("parse token:"+ret+",cost:"+(et-st)+" ms.");
            Assert.assertEquals(ret.getUid(), uid);
            Assert.assertEquals(ret.getTimestamp(), timestamp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
