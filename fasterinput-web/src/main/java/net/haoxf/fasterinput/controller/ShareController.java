package net.haoxf.fasterinput.controller;

import net.haoxf.fasterinput.consts.Consts.Code;
import net.haoxf.fasterinput.model.HttpRet;
import net.haoxf.fasterinput.model.Share;
import net.haoxf.fasterinput.service.ShareService;
import net.haoxf.fasterinput.utils.CodecUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    ){
        Assert.notNull(content, "share content cannot null");
        String md5 = CodecUtil.md5(content);
        Share exist = shareService.getByMd5(md5);
        if(exist != null){
            return new HttpRet(Code.SUCCESS.getCode(), "success", exist.getId());
        }else{
            Share share = new Share(md5, new Date(), content);
            shareService.addShare(share);
            return new HttpRet(Code.SUCCESS.getCode(), "success", share.getId());
        }
    }
}
