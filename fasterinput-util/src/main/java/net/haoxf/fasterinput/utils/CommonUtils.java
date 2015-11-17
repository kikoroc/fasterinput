package net.haoxf.fasterinput.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/11/17 0017.
 */
public class CommonUtils {

    private static final Pattern legalCharacters = Pattern.compile("[a-zA-Z0-9_0x4E00-0x9FCB]");

    public static boolean containIllegalCharacters(String source){
        Matcher matcher = legalCharacters.matcher(source);
        return !matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(containIllegalCharacters("ds1AadZd1_DA23a1D321_岁的哈苏德"));
    }

}
