package net.haoxf.fasterinput.utils;

import net.haoxf.fasterinput.consts.Consts;
import net.haoxf.fasterinput.exceptions.SecretKeyLengthException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Random;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-10-25
 */
public class AuthUtil {
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
    public static String generateToken(long uid, long timestamp) throws NoSuchPaddingException, NoSuchAlgorithmException,
            NoSuchProviderException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, SecretKeyLengthException {
        secretKeyLegal(Consts.SECRET_KEY);
        //pc上的java只有PKCS5Padding，没有PKCS7Padding，所以需要引入BouncyCastle库，并在这里传入"BC"参数指定使用这个库的加解密算法
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        SecretKey secretKey = new SecretKeySpec(Consts.SECRET_KEY.getBytes(), KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));

        String macData = uid + "|" + timestamp;
        byte[] retBytes = cipher.doFinal(macData.getBytes());
        return new String(new Hex().encode(retBytes));
    }

    public static AuthRet parseToken(String token) throws NoSuchPaddingException, NoSuchAlgorithmException,
            NoSuchProviderException, InvalidAlgorithmParameterException, InvalidKeyException, DecoderException,
            BadPaddingException, IllegalBlockSizeException, SecretKeyLengthException {
        secretKeyLegal(Consts.SECRET_KEY);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        SecretKey secretKey = new SecretKeySpec(Consts.SECRET_KEY.getBytes(), KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));

        byte[] retBytes = cipher.doFinal(new Hex().decode(token.getBytes()));
        String ret = new String(retBytes);
        return new AuthRet(Long.valueOf(ret.split("\\|")[0]), Long.valueOf(ret.split("\\|")[1]));
    }

    private static void secretKeyLegal(String secretKey) throws SecretKeyLengthException {
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
