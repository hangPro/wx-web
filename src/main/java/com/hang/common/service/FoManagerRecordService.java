package com.hang.common.service;

import com.hang.common.domain.FoManager;
import com.hang.common.domain.FoManagerRecord;
import com.hang.common.mapper.FoManagerRecordMapper;
import com.hang.common.utils.AchieveUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class FoManagerRecordService {

    @Autowired
    FoManagerRecordMapper foManagerRecordMapper;

    /**
     * 添加管理员登录日志信息
     * */
    public int insertSelective(FoManager foManager, HttpServletRequest request){
        FoManagerRecord foManagerRecord = new FoManagerRecord();
        foManagerRecord.setUserId(foManager.getId());
        foManagerRecord.setUsername(foManager.getUsername());
        foManagerRecord.setUname(foManager.getUname());
        foManagerRecord.setIp(AchieveUtil.getIpAddr(request));
        foManagerRecord.setCtime(AchieveUtil.getDateTime(""));
        foManagerRecord.setBrowser(AchieveUtil.getSystemBrowser(request,"browser"));
        foManagerRecord.setSysteminfo(AchieveUtil.getSystemBrowser(request,"system"));
        return foManagerRecordMapper.insertSelective(foManagerRecord);
    }

    /**
     * 管理员日志列表
     * */
    public List<FoManagerRecord> selectJoinFoManager(FoManagerRecord foManagerRecord){
        if (foManagerRecord.getPage() != null && foManagerRecord.getRows() != null) {
            PageHelper.startPage(foManagerRecord.getPage(), foManagerRecord.getRows());
        }
        return foManagerRecordMapper.selectJoinFoManager(foManagerRecord.getSearch());
    }

    /**
     * 管理员日志删除
     * */
    public int deleteByPrimaryKey(FoManagerRecord foManagerRecord){
        return foManagerRecordMapper.deleteByPrimaryKey(foManagerRecord.getId());
    }

}
