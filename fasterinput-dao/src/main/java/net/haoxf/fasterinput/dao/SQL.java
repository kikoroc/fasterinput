package net.haoxf.fasterinput.dao;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-11-4
 */
public class SQL {
    public static final String ADD_SHARE =
            "insert into tb_share(md5,shareTime,content) values (?,?,?)";
    public static final String GET_SHARE_BY_ID =
            "select * from tb_share where id = ?";
    public static final String GET_SHARE_BY_MD5 =
            "select * from tb_share where md5 = ?";

    public static final String ADD_ACCOUNT =
            "insert into tb_account(accountFrom,openId,enable,regDate,lastLogin,nickName,gender,avatarUrl) values (" +
                    "?,?,?,?,?,?,?,?)";
    public static final String QUERY_ACCOUNT_BY_ID =
            "select * from tb_account where id = ?";
    public static final String QUERY_ACCOUNT_BY_THIRD =
            "select * from tb_account where accountFrom = ? and openId = ?";

}
