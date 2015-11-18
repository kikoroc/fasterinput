package net.haoxf.fasterinput.exceptions;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-11-18
 */
public class ThirdAccessTokenIllegalException extends RuntimeException {

    private int from;
    private String token;

    public ThirdAccessTokenIllegalException(int from, String token, String msg){
        super(msg);
        this.from = from;
        this.token = token;
    }

}
