package net.haoxf.fasterinput.exceptions;

import net.haoxf.fasterinput.consts.Consts;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-11-18
 */
public class ThirdAccessTokenIllegalException extends RuntimeException {

    private Consts.ThirdAccount from;
    private String token;

    public ThirdAccessTokenIllegalException(Consts.ThirdAccount from, String token, String msg){
        super(msg);
        this.from = from;
        this.token = token;
    }

}
