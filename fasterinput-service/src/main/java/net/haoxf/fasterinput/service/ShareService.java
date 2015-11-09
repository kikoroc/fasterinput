package net.haoxf.fasterinput.service;

import net.haoxf.fasterinput.dao.ShareDao;
import net.haoxf.fasterinput.model.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-11-4
 */
@Service("shareService")
public class ShareService {

    @Autowired
    @Qualifier("shareDaoMysqlImpl")
    private ShareDao shareDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addShare(Share[] shares){
        for(Share share : shares){
            shareDao.addShare(share);
        }
    }

    @Transactional(readOnly = true)
    public Share getById(long id){
        return shareDao.getById(id);
    }

    @Transactional(readOnly = true)
    public Share getByMd5(String md5){
        return shareDao.getByMd5(md5);
    }
}
