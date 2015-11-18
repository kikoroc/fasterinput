package net.haoxf.fasterinput.exceptions;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-10-25
 */
public class TokenException extends RuntimeException {
    private String token;

    public TokenException() {
    }

    public TokenException(String token, String message){
        super(message);
        this.token = token;
    }

    public TokenException(String message) {
        super(message);
    }
}
