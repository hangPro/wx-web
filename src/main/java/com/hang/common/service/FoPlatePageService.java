package com.hang.common.service;

import com.github.pagehelper.PageHelper;
import com.hang.common.domain.FoPlatePage;
import com.hang.common.mapper.FoPlatePageMapper;
import com.hang.common.utils.AchieveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName FoPlateService
 * @Author hang
 * @DATE 2018/11/14 14:57
 * @Description TODO
 **/
@Service
public class FoPlatePageService {

    @Autowired
    FoPlatePageMapper platePageMapper;

    /**
     * 新增板块-操作，检查板块是否存在
     * */
    public boolean insertSelective(FoPlatePage platePage){
        FoPlatePage useone = platePageMapper.selectByName(platePage.getName());
        if(useone != null){
            return false;
        }else{
            platePage.setCtime(AchieveUtil.getDateTime(""));
            platePageMapper.insertSelective(platePage);
            return true;
        }
    }

    /**
     * 板块列表
     * @param foPlatePage 得到对应地址栏的参数的对象
     * */
    public List<FoPlatePage> selectByList(FoPlatePage foPlatePage){
        if (foPlatePage.getPage() != null && foPlatePage.getRows() != null) {
            PageHelper.startPage(foPlatePage.getPage(), foPlatePage.getRows());
        }
        List<FoPlatePage> fmr = platePageMapper.selectByList(foPlatePage.getSearch());
        return fmr;
    }

    /**
     * 板块
     * @param id 得到对应地址栏的参数的对象
     * */
    public FoPlatePage selectByPrimaryKey(Integer id){
        FoPlatePage foPlate = platePageMapper.selectByPrimaryKey(id);
        return foPlate;
    }

    /**
     * 编辑
     * */
    public int updateByPrimaryKeySelective(FoPlatePage foPlate){
        return platePageMapper.updateByPrimaryKeySelective(foPlate);
    }

    public void deleteByPrimaryKey(FoPlatePage foPlatePage){
        platePageMapper.deleteByPrimaryKey(foPlatePage.getId());
    }
}
