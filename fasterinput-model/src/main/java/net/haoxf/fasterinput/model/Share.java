package net.haoxf.fasterinput.model;

import java.util.Date;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-11-4
 */
public class Share {
    private long id;
    private String md5;
    private Date shareTime;
    private String content;

    public Share() {
    }

    public Share(long id, String md5, Date shareTime, String content) {
        this.id = id;
        this.md5 = md5;
        this.shareTime = shareTime;
        this.content = content;
    }

    public Share(String md5, Date shareTime, String content) {
        this.md5 = md5;
        this.shareTime = shareTime;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
