package com.hang.admin.controller;

import com.github.pagehelper.PageInfo;
import com.hang.common.domain.AdminTitle;
import com.hang.common.domain.FoInfo;
import com.hang.common.domain.FoPlate;
import com.hang.common.service.FoPlateService;
import com.hang.common.utils.PageUtil;
import com.hang.common.utils.WebResultUtil;
import com.hang.utils.PropertieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Paths;
import java.util.List;

/**
 * @ClassName PlateController
 * @Author hang
 * @DATE 2018/11/14 15:04
 * @Description TODO
 **/
@Controller
@RequestMapping(value = "/admin")
public class PlateController {

    @Autowired
    FoPlateService foPlateService;
    @Autowired
    ResourceLoader resourceLoader;

    private String uploadPath = PropertieUtils.getString("upload.img.path");

    /**
     * 板块管理列表
     * */
    @GetMapping(value = "/plate/list")
    public String plateList(ModelMap map, HttpServletRequest request, FoPlate fp){
        AdminTitle adminTitle = new AdminTitle();
        adminTitle.setTitle1("板块管理");
        adminTitle.setTitle2("列表");
        map.addAttribute("adminTitle", adminTitle);

        //管理员分组列表，分页
        List<FoPlate> foPlates = foPlateService.selectByList(fp);
        PageInfo pageInfo = new PageInfo<FoPlate>(foPlates);
        map.addAttribute("pageInfo",pageInfo);
        map.addAttribute("pageshow", PageUtil.show(pageInfo,request));

        return "admin/plate/list";
    }

    /**
     * 添加功能
     * */
    @GetMapping(value = "/plate/addGet")
    public String plateGet(ModelMap map){
        AdminTitle adminTitle = new AdminTitle();
        adminTitle.setTitle1("板块管理");adminTitle.setTitle2("新增");
        map.addAttribute("adminTitle", adminTitle);
        return "admin/plate/add";
    }

    /**
     * 添加功能
     * */
    @RequestMapping(value = "/plate/add")
    public String plateAdd(ModelMap map, FoPlate foPlate/*,@RequestParam(value = "uploadfile") MultipartFile file*/){
		/**
        String fileName = file.getOriginalFilename();
        //保存
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(uploadPath, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        foPlate.setImgurl(fileName);
         */
        boolean result = foPlateService.insertSelective(foPlate);
        if(result){
            return WebResultUtil.success(map,"板块新增成功！","/admin/plate/list");
        }else{
            return WebResultUtil.error(map,"板块"+foPlate.getName()+"已存在，请更换！！","/admin/plate/addGet");
        }
    }

    @RequestMapping(value = "/showImg", name = "获取图片")
    public ResponseEntity showImg(@RequestParam(name = "fileName") String fileName) {
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(uploadPath, fileName).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 编辑功能
     * */
    @GetMapping(value = "/plate/upGet")
    public String upGet(ModelMap map,FoPlate foPlate){
        AdminTitle adminTitle = new AdminTitle();
        adminTitle.setTitle1("板块管理");adminTitle.setTitle2("编辑");
        map.addAttribute("adminTitle", adminTitle);
        map.addAttribute("foPlate",foPlateService.selectByPrimaryKey(foPlate.getId()));
        return "admin/plate/up";
    }

    /**
     * 编辑功能
     * */
    @RequestMapping(value = "/plate/up")
    public String up(ModelMap map,FoPlate foPlate/**,@RequestParam(value = "uploadfile") MultipartFile file*/){
        /**
        String fileName = file.getOriginalFilename();
        //保存
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(uploadPath, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        foPlate.setImgurl(fileName);*/
        int resutl = foPlateService.updateByPrimaryKeySelective(foPlate);
        if(resutl == 1) {
            return WebResultUtil.success(map, "板块编辑成功！", "/admin/plate/list");
        }else {
            return WebResultUtil.error(map,"板块编辑失败！！","/admin/plate/upGet");
        }
    }

    /**
     * 删除操作
     * */
    @GetMapping(value = "/plate/del")
    public void plateDel(FoPlate foPlate, HttpServletResponse response) throws Exception{
        foPlateService.deleteByPrimaryKey(foPlate);
        response.sendRedirect("/admin/plate/list");
    }



    /**
     * 编辑首页信息功能
     * */
    @RequestMapping(value = "/plate/upInfo")
    public String upInfo(ModelMap map, FoInfo foInfo,HttpServletRequest request){
        if(request.getMethod().equals("GET")) {
            AdminTitle adminTitle = new AdminTitle();
            adminTitle.setTitle1("首页信息管理");
            adminTitle.setTitle2("编辑");
            map.addAttribute("adminTitle", adminTitle);
            map.addAttribute("foInfo",foPlateService.getInfo());
        }else if (request.getMethod().equals("POST")) {
            int resutl = foPlateService.upInfo(foInfo);
            if (resutl == 1) {
                return WebResultUtil.success(map, "编辑成功！", "/admin/plate/upInfo");
            } else {
                return WebResultUtil.error(map, "编辑失败！！", "/admin/plate/upInfo");
            }
        }
        return "admin/plate/upInfo";
    }
}
