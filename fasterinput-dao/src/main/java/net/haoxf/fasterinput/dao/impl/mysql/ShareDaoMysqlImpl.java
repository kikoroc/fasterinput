package net.haoxf.fasterinput.dao.impl.mysql;

import net.haoxf.fasterinput.dao.SQL;
import net.haoxf.fasterinput.dao.ShareDao;
import net.haoxf.fasterinput.model.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-11-4
 */
@Repository("shareDaoMysqlImpl")
public class ShareDaoMysqlImpl implements ShareDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addShare(final Share share) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(SQL.ADD_SHARE, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, share.getMd5());
                ps.setDate(2, new java.sql.Date(share.getShareTime().getTime()));
                ps.setString(3, share.getContent());
                return ps;
            }
        }, key);

        share.setId(key.getKey().longValue());
    }

    @Override
    public Share getById(long id) {
        List<Share> rets = jdbcTemplate.query(SQL.GET_SHARE_BY_ID,
                new Object[]{id}, shareRowMapper());
        if(rets.size()>0){
            return rets.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Share getByMd5(String md5) {
        List<Share> rets = jdbcTemplate.query(SQL.GET_SHARE_BY_MD5,
                new Object[]{md5},
                shareRowMapper());
        if(rets.size()>0){
            return rets.get(0);
        }else{
            return null;
        }
    }

    private RowMapper<Share> shareRowMapper(){
        return new RowMapper<Share>() {
            @Override
            public Share mapRow(ResultSet rs, int rowNum) throws SQLException {
                Share share = new Share();
                share.setId(rs.getLong("id"));
                share.setContent(rs.getString("content"));
                share.setMd5(rs.getString("md5"));
                share.setShareTime(new Date(rs.getDate("shareTime").getTime()));

                return share;
            }
        };
    }
}
