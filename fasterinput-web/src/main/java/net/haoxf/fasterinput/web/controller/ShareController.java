package net.haoxf.fasterinput.web.controller;

import net.haoxf.fasterinput.consts.Consts.Code;
import net.haoxf.fasterinput.model.HttpRet;
import net.haoxf.fasterinput.model.Share;
import net.haoxf.fasterinput.service.ShareService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-11-4
 */
@RestController
public class ShareController extends BaseController {

    @Autowired
    private ShareService shareService;

    @RequestMapping(value = "/api/share", method = RequestMethod.POST)
    public HttpRet addShare(
            @RequestParam("content") String content
    ) throws UnsupportedEncodingException {
        Assert.notNull(content, "share content cannot null");
        String md5 = DigestUtils.md5Hex(content);
        Share exist = shareService.getByMd5(md5);
        if(exist != null){
            return new HttpRet(Code.SUCCESS.getCode(), "success", exist.getId());
        }else{
            Share share = new Share(md5, new Date(), content);
            shareService.addShare(share);
            return new HttpRet(Code.SUCCESS.getCode(), "success", share.getId());
        }
    }

    @RequestMapping("/api/share/{id}")
    public HttpRet shareInfo(@PathVariable long id){
        Share share = shareService.getById(id);
        return new HttpRet(Code.SUCCESS.getCode(), "success", share);
    }
}
