package net.haoxf.fasterinput.utils;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/11/17 0017.
 */
public class CommonUtils {

    private static final String legalCharactersRegex = "[a-zA-Z0-9_\u4E00-\u9FCB]+";

    /**
     * 检查source中是否包含大小写英文字母、数字、下划线、中文之外的非法字符
     * @param source
     * @return
     */
    public static boolean containIllegalCharacters(String source){
        return !Pattern.matches(legalCharactersRegex, source);
    }

    public static void main(String[] args) {
        System.out.println(containIllegalCharacters("dsaA09_点撒"));
        System.out.println(containIllegalCharacters("dsaA09_点撒３２ｄｓ"));
    }

}
