package net.haoxf.fasterinput.dao;

import net.haoxf.fasterinput.model.Share;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-11-4
 */
public interface ShareDao {

    /**
     * get insert id with share.getId()
     * @param share
     */
    public void addShare(Share share);

    public Share getById(long id);

    public Share getByMd5(String md5);

}
