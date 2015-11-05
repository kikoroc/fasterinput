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

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-11-4
 */
@Repository
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
        return jdbcTemplate.queryForObject(SQL.GET_SHARE_BY_ID,
                new Object[]{id}, shareRowMapper());
    }

    @Override
    public Share getByMd5(String md5) {
        return jdbcTemplate.queryForObject(SQL.GET_SHARE_BY_MD5,
                new Object[]{md5},
                shareRowMapper());
    }

    private RowMapper<Share> shareRowMapper(){
        return new RowMapper<Share>() {
            @Override
            public Share mapRow(ResultSet rs, int rowNum) throws SQLException {
                Share share = new Share();
                share.setId(rs.getLong("id"));
                share.setContent(rs.getString("content"));
                share.setMd5(rs.getString("md5"));
                share.setShareTime(rs.getDate("shareTime"));

                return share;
            }
        };
    }
}
