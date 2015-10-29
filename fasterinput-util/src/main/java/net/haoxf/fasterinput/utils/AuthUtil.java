package net.haoxf.fasterinput.utils;

import net.haoxf.fasterinput.consts.Consts;
import net.haoxf.fasterinput.exceptions.TokenException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.util.Random;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-10-25
 */
public class AuthUtil {
    protected static Log log = LogFactory.getLog(AuthUtil.class);
    private static final String KEY_ALGORITHM = "AES";
    /**
     * @see //blog.poxiao.me/p/advanced-encryption-standard-and-block-cipher-mode/
     */
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";
    private static final byte[] ivBytes;
    static {
        Security.addProvider(new BouncyCastleProvider());
        //generate random ivbytes.
        Random random = new Random();
        byte[] bytes = new byte[16];
        for(int i = 0; i < bytes.length; i++){
            bytes[i] = (byte) random.nextInt(Byte.MAX_VALUE + 1);
        }
        ivBytes = bytes;
    }

    /**
     *
     * @param uid
     * @param timestamp
     * @return
     */
    public static String generateToken(long uid, long timestamp) throws TokenException {
        try {
            secretKeyLegal(Consts.SECRET_KEY);
            //pc上的java只有PKCS5Padding，没有PKCS7Padding，所以需要引入BouncyCastle库，并在这里传入"BC"参数指定使用这个库的加解密算法
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
            SecretKey secretKey = new SecretKeySpec(Consts.SECRET_KEY.getBytes(), KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));

            String macData = uid + "|" + timestamp;
            byte[] retBytes = cipher.doFinal(macData.getBytes());
            return new String(new Hex().encode(retBytes));
        }catch(Exception ex){
            log.error("生成token出错。", ex);
            throw new TokenException("生成token失败。");
        }
    }

    public static AuthRet parseToken(String token) throws TokenException {
        try {
            secretKeyLegal(Consts.SECRET_KEY);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
            SecretKey secretKey = new SecretKeySpec(Consts.SECRET_KEY.getBytes(), KEY_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));

            byte[] retBytes = cipher.doFinal(new Hex().decode(token.getBytes()));
            String ret = new String(retBytes);
            return new AuthRet(Long.valueOf(ret.split("\\|")[0]), Long.valueOf(ret.split("\\|")[1]));
        }catch(Exception ex){
            log.error("解析token失败。", ex);
            throw new TokenException("解析token失败。");
        }
    }

    private static void secretKeyLegal(String secretKey) {
        if(secretKey.length()!=16){
            throw new IllegalArgumentException("密钥必须是16位的字符串。");
        }
    }

    public static class AuthRet {
        private long uid;
        private long timestamp;

        AuthRet(long uid, long timestamp) {
            this.uid = uid;
            this.timestamp = timestamp;
        }

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public String toString(){
            return "uid:"+getUid()+",timestamp:"+getTimestamp();
        }
    }

}
