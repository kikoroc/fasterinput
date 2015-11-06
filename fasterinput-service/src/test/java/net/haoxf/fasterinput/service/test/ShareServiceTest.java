package net.haoxf.fasterinput.service.test;

import net.haoxf.fasterinput.dao.DataSourceConfiguration;
import net.haoxf.fasterinput.model.Share;
import net.haoxf.fasterinput.service.ServiceConfiguration;
import net.haoxf.fasterinput.service.ShareService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by Administrator on 2015/11/6 0006.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ServiceConfiguration.class, DataSourceConfiguration.class})
public class ShareServiceTest {

    @Autowired
    private ShareService shareService;

    @Test
    public void addShares(){
        Share share1 = new Share();
        String content = "testShare1";
        share1.setContent(content);
        share1.setMd5(DigestUtils.md5Hex(content));
        share1.setShareTime(new Date());

        Share share2 = new Share();
        share2.setContent(content);
        share2.setMd5(DigestUtils.md5Hex(content));
        share2.setShareTime(new Date());

        Share[] shares = new Share[]{share1, share2};

        shareService.addShare(shares);
    }
}
