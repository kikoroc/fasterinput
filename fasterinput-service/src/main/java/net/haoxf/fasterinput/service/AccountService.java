package net.haoxf.fasterinput.service;

import net.haoxf.fasterinput.dao.AccountDao;
import net.haoxf.fasterinput.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/11/19 0019.
 */
@Service("accountService")
public class AccountService {

    @Autowired
    @Qualifier("accountDaoMysqlImpl")
    private AccountDao accountDao;

    public void addAccount(Account account){
        accountDao.addAccount(account);
    }

    public Account queryAccount(int accountFrom, String openId){
        return accountDao.queryByThirdAccount(accountFrom, openId);
    }

    public Account queryAccount(long id){
        return accountDao.queryById(id);
    }
}
