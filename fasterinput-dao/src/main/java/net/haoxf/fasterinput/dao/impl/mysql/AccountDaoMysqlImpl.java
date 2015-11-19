package net.haoxf.fasterinput.dao.impl.mysql;

import net.haoxf.fasterinput.dao.AccountDao;
import net.haoxf.fasterinput.dao.SQL;
import net.haoxf.fasterinput.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

/**
 * Created by Administrator on 2015/11/19 0019.
 */
@Repository("accountDaoMysqlImpl")
public class AccountDaoMysqlImpl implements AccountDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addAccount(final Account account) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final Date now = new Date(System.currentTimeMillis());
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                //accountFrom,openId,enable,regDate,lastLogin,nickName,gender,avatarUrl)
                PreparedStatement ps = con.prepareStatement(SQL.ADD_ACCOUNT, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, account.getAccountFrom());
                ps.setString(2, account.getOpenId());
                ps.setInt(3, 1);
                ps.setDate(4, now);
                ps.setDate(5, now);
                ps.setString(6, account.getNickName());
                ps.setInt(7, account.getGender());
                ps.setString(8, account.getAvatarUrl());
                return ps;
            }
        }, keyHolder);

        account.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void updateAccount(Account account) {
        //TODO
    }

    @Override
    public Account queryByThirdAccount(int accountFrom, String openId){
        List<Account> rets = jdbcTemplate.query(SQL.QUERY_ACCOUNT_BY_THIRD, new Object[]{
                accountFrom, openId
        }, accountRowMapper);
        if(rets.size()>0){
            return rets.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Account queryById(long id) {
        List<Account> rets = jdbcTemplate.query(SQL.QUERY_ACCOUNT_BY_ID, new Object[]{id}, accountRowMapper);
        if(rets.size()>0){
            return rets.get(0);
        }else{
            return null;
        }
    }

    private RowMapper<Account> accountRowMapper = new RowMapper<Account>() {
        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            Account account = new Account();
            account.setId(rs.getLong("id"));
            account.setAccountFrom(rs.getInt("accountFrom"));
            account.setOpenId(rs.getString("openId"));
            account.setEnable(rs.getInt("enable"));
            account.setRegDate(new java.util.Date(rs.getDate("regDate").getTime()));
            account.setLastLogin(new java.util.Date(rs.getDate("lastLogin").getTime()));
            account.setNickName(rs.getString("nickName"));
            account.setGender(rs.getInt("gender"));
            account.setAvatarUrl(rs.getString("avatarUrl"));

            return account;
        }
    };
}
