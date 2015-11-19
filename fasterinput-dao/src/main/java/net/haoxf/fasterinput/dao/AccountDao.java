package net.haoxf.fasterinput.dao;

import net.haoxf.fasterinput.model.Account;

/**
 * Created by Administrator on 2015/11/19 0019.
 */
public interface AccountDao {

    void addAccount(Account account);
    void updateAccount(Account account);
    Account queryByThirdAccount(int accountFrom, String openId);
    Account queryById(long id);

}
