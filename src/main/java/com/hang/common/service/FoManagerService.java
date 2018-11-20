package com.hang.common.service;

import com.hang.common.domain.FoManager;
import com.hang.common.domain.FoManagerGroup;
import com.hang.common.enums.JsonResultEnum;
import com.hang.common.exception.JsonException;
import com.hang.common.mapper.FoManagerMapper;
import com.hang.common.utils.AchieveUtil;
import com.hang.common.utils.MD5Util;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class FoManagerService {

    @Autowired
    FoManagerMapper foManagerMapper;

    @Autowired
    FoManagerGroupService foManagerGroupService;

    /**
     * 根据用户ID查询
     * */
    public FoManager selectByPrimaryKey(Integer id){
        return foManagerMapper.selectByPrimaryKey(id);
    }

    /**
    * 管理员登录，判断用户存在、验证密码、用户是否被锁定
    * @param username 用户名
    * @param password 密码
    * */
    public FoManager loginAction(String username,String password) throws Exception{
        FoManager foManager = foManagerMapper.selectByUsername(username,null);
        if(foManager == null){
            throw new JsonException(JsonResultEnum.ADMIN_USER_NULL);
        }else if(!MD5Util.string2MD5(password).equals(foManager.getPassword())) {
            throw new JsonException(JsonResultEnum.ADMIN_PASS_ERROR);
        }else if(foManager.getLocking() == 1){
            throw new JsonException(JsonResultEnum.ADMIN_USER_LOCKING);
        }
        return foManager;
    }

    /**
    * 编辑管理员用户信息
    * @param foManager Controller中得到的用户对象
    * @param request 当前http请求
    * */
    public int updateByPrimaryKeySelective(FoManager foManager, HttpServletRequest request){
        foManager.setLoginIp(AchieveUtil.getIpAddr(request));
        foManager.setNumber(foManager.getNumber() + 1);
        foManager.setLoginTime(AchieveUtil.getDateTime(""));
        return foManagerMapper.updateByPrimaryKeySelective(foManager);
    }

    /**
     * 管理员列表
     * @param foManager 得到对应地址栏的参数的对象
     * */
    public List<FoManager> selectByList(FoManager foManager){
        if (foManager.getPage() != null && foManager.getRows() != null) {
            PageHelper.startPage(foManager.getPage(), foManager.getRows());
        }
        List<FoManager> fmr = foManagerMapper.selectByList(foManager.getGroupId(),foManager.getSearch());
        for(FoManager fo : fmr){//循环嵌套
            FoManagerGroup foManagerGroup = foManagerGroupService.selectByPrimaryKey(fo.getGroupId());
            fo.setGroupName(foManagerGroup.getGname());//得到用户组名称
        }
        return fmr;
    }

    /**
     * 新增管理员-操作，检查用户是否存在
     * */
    public boolean insertSelective(FoManager foManager,HttpServletRequest request){
        FoManager userOne = foManagerMapper.selectByUsername(foManager.getUsername(),null);
        if(userOne != null){
            return false;
        }else{
            foManager.setLoginIp(AchieveUtil.getIpAddr(request));
            foManager.setCtime(AchieveUtil.getDateTime(""));
            foManager.setPassword(MD5Util.string2MD5(foManager.getPassword()));
            foManagerMapper.insertSelective(foManager);
            return true;
        }
    }

    /**
     * 编辑管理员信息
     * */
    public boolean updateByPrimaryKeySelectivePassword(FoManager foManager){
        FoManager userOne = foManagerMapper.selectByUsername(foManager.getUsername(),foManager.getId());
        if(userOne != null){
            return false;
        }else {
            if ("".equals(foManager.getPassword())) {
                //密码为空则不修改
                foManager.setPassword(null);
            } else {
                foManager.setPassword(MD5Util.string2MD5(foManager.getPassword()));
            }
            foManagerMapper.updateByPrimaryKeySelective(foManager);
            return true;
        }
    }

    /**
    * 管理员密码修改
    * */
    public boolean updatePassword(FoManager foManager, int adminId){
        FoManager adminuser = foManagerMapper.selectByPrimaryKey(adminId);
        //判断旧密码是否正确
        if(adminuser.getPassword().equals(MD5Util.string2MD5(foManager.getOldpassword()))){
            foManager.setId(adminId);
            foManager.setPassword(MD5Util.string2MD5(foManager.getPassword()));
            foManagerMapper.updateByPrimaryKeySelective(foManager);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 查询管理员总数
     * */
    public int selectByCount(){
        return foManagerMapper.selectByCount();
    }

    /**
     * 删除管理员操作
     * */
    public int deleteByPrimaryKey(int id){
        return foManagerMapper.deleteByPrimaryKey(id);
    }

}
