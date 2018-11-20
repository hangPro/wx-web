package com.hang.admin.controller;

import com.github.pagehelper.PageInfo;
import com.hang.common.domain.AdminTitle;
import com.hang.common.domain.FoPlate;
import com.hang.common.domain.FoPlatePage;
import com.hang.common.service.FoPlatePageService;
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
public class PlatePageController {

    @Autowired
    FoPlatePageService foPlatePageService;
    @Autowired
    FoPlateService foPlateService;
    @Autowired
    ResourceLoader resourceLoader;

    private String uploadPath = PropertieUtils.getString("upload.img.path");

    /**
     * 板块管理列表
     * */
    @GetMapping(value = "/platePage/list")
    public String platePageList(ModelMap map, HttpServletRequest request, FoPlatePage fp){
        AdminTitle adminTitle = new AdminTitle();
        adminTitle.setTitle1("板块管理");
        adminTitle.setTitle2("列表");
        map.addAttribute("adminTitle", adminTitle);

        //管理员分组列表，分页
        List<FoPlatePage> foPlates = foPlatePageService.selectByList(fp);
        PageInfo pageInfo = new PageInfo<>(foPlates);
        map.addAttribute("pageInfo",pageInfo);
        map.addAttribute("pageshow", PageUtil.show(pageInfo,request));

        return "admin/platePage/list";
    }

    /**
     * 添加功能
     * */
    @GetMapping(value = "/platePage/addGet")
    public String platePageGet(ModelMap map){
        AdminTitle adminTitle = new AdminTitle();
        adminTitle.setTitle1("板块管理");adminTitle.setTitle2("新增");
        map.addAttribute("adminTitle", adminTitle);
        map.addAttribute("plateList", foPlateService.selectByList(new FoPlate()));
        return "admin/platePage/add";
    }

    /**
     * 添加功能
     * */
    @RequestMapping(value = "/platePage/add")
    public String platePageAdd(ModelMap map, FoPlatePage foPlate){
        boolean result = foPlatePageService.insertSelective(foPlate);
        if(result){
            return WebResultUtil.success(map,"新增成功！","/admin/platePage/list");
        }else{
            return WebResultUtil.error(map,"板块"+foPlate.getName()+"已存在，请更换！！","/admin/platePage/addGet");
        }
    }

    @RequestMapping(value = "/platePage/showImg", name = "获取图片")
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
    @GetMapping(value = "/platePage/upGet")
    public String upGet(ModelMap map,FoPlatePage foPlate){
        AdminTitle adminTitle = new AdminTitle();
        adminTitle.setTitle1("板块管理");adminTitle.setTitle2("编辑");
        map.addAttribute("adminTitle", adminTitle);
        map.addAttribute("foPlatePage",foPlatePageService.selectByPrimaryKey(foPlate.getId()));
        map.addAttribute("plateList", foPlateService.selectByList(new FoPlate()));
        return "admin/platePage/up";
    }

    /**
     * 编辑功能
     * */
    @RequestMapping(value = "/platePage/up")
    public String up(ModelMap map,FoPlatePage foPlate/*,@RequestParam(value = "uploadfile") MultipartFile file*/){
        /*
        String fileName = file.getOriginalFilename();
        //保存
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(uploadPath, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        foPlate.setImgurl(fileName);
        */
        int resutl = foPlatePageService.updateByPrimaryKeySelective(foPlate);
        if(resutl == 1) {
            return WebResultUtil.success(map, "板块编辑成功！", "/admin/platePage/list");
        }else {
            return WebResultUtil.error(map,"板块编辑失败！！","/admin/platePage/upGet");
        }
    }

    /**
     * 删除操作
     * */
    @GetMapping(value = "/platePage/del")
    public void plateDel(FoPlatePage foPlatePage, HttpServletResponse response) throws Exception{
        foPlatePageService.deleteByPrimaryKey(foPlatePage);
        response.sendRedirect("/admin/platePage/list");
    }
}
