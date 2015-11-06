package net.haoxf.fasterinput.dao.test;

import com.google.gson.Gson;
import net.haoxf.fasterinput.dao.DataSourceConfiguration;
import net.haoxf.fasterinput.dao.ShareDao;
import net.haoxf.fasterinput.model.Share;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-11-5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DataSourceConfiguration.class})
public class ShareDaoTest {

    @Autowired
    private ShareDao shareDaoMysqlImpl;
    private Gson gson = new Gson();

    @Test
    public void addShare(){
        Share share = new Share();
        String content = "testShare";
        share.setContent(content);
        share.setMd5(DigestUtils.md5Hex(content));
        share.setShareTime(new Date());

        shareDaoMysqlImpl.addShare(share);
    }

    @Test
    public void getById(){
        Share share = shareDaoMysqlImpl.getById(1l);
        System.out.println(gson.toJson(share));
    }

    @Test
    public void getByMd5(){
        Share share = shareDaoMysqlImpl.getByMd5("6475530805e616374c81e30616e006aa");
        System.out.println(gson.toJson(share));

        Share share2 = shareDaoMysqlImpl.getByMd5("6475530805e616374c81e30616e006ab");
        System.out.println(gson.toJson(share2));
    }

}
