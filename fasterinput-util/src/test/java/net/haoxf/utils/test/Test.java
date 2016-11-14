package net.haoxf.utils.test;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2016/4/14 0014.
 */
public class Test {
    public static void main(String[] args) throws Exception {

        /*String key = "testtesttesttest";       // key는 16자 이상
        Aes256 aes256 = new Aes256(key);

        String ret = aes256.aesDecode("5127a8a4eb6f5297a4df78c7552438b64835841d044ac014130ead0866c169cd");
        System.out.println(ret);*/
        AESAlgorithm aes = new AESAlgorithm();
        System.out.println(aes.decrypt("7d3aa52805c90afb47f4f3253842ce83"));
    }
}
