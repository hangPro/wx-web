package com.hang.web;

import com.alibaba.fastjson.JSONObject;
import com.hang.common.domain.FoPlate;
import com.hang.common.service.FoPlatePageService;
import com.hang.common.service.FoPlateService;
import com.hang.config.WxMpConfiguration;
import com.hang.utils.PropertieUtils;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    @Autowired
    FoPlateService foPlateService;
    @Autowired
    FoPlatePageService foPlatePageService;

    @RequestMapping("/mainPage")
    public ModelAndView mainPage(ModelAndView mav, @RequestParam(value = "id", required = false) Integer id) throws Exception {
        String url = PropertieUtils.getString("wx.url");
        String appid = PropertieUtils.getString("wx.appId");
        final WxMpService wxService = WxMpConfiguration.getMpServices().get(appid);
        WxJsapiSignature signature = wxService.createJsapiSignature(url);
        mav.addObject("appId", appid);
        mav.addObject("timestamp", signature.getTimestamp());
        mav.addObject("nonceStr", signature.getNonceStr());
        mav.addObject("signature", signature.getSignature());
        mav.addObject("id", id);
        mav.addObject("plateList", foPlateService.selectByList(new FoPlate()));
        mav.setViewName("mainPage");
        return mav;
    }

    @RequestMapping("/")
    public ModelAndView page(ModelAndView mav, @RequestParam(value = "id", required = false) Integer id) {
        mav.addObject("pageList", foPlateService.selectPlatePageList());
        if (id != null) {
            mav.addObject("pageInfo", foPlatePageService.selectByPrimaryKey(id));
        }
        mav.addObject("info", foPlateService.getInfo());
        mav.setViewName("page");
        return mav;
    }

    @RequestMapping("/pageJson")
    @ResponseBody
    public JSONObject pageJson(@RequestParam(value = "url") String url) throws Exception {
        String appid = PropertieUtils.getString("wx.appId");
        final WxMpService wxService = WxMpConfiguration.getMpServices().get(appid);
        WxJsapiSignature signature = wxService.createJsapiSignature(url);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appId", appid);
        jsonObject.put("timestamp", signature.getTimestamp());
        jsonObject.put("nonceStr", signature.getNonceStr());
        jsonObject.put("signature", signature.getSignature());
        return jsonObject;
    }

    @RequestMapping("/list")
    public ModelAndView list(ModelAndView mav) {
        mav.addObject("info", foPlateService.getInfo());
        mav.setViewName("list");
        return mav;
    }
}