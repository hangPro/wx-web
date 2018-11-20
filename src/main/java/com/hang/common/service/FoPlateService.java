package com.hang.common.service;

import com.hang.common.domain.FoInfo;
import com.hang.common.domain.FoPlate;
import com.github.pagehelper.PageHelper;
import com.hang.common.domain.FoPlatePage;
import com.hang.common.domain.Page;
import com.hang.common.mapper.FoInfoMapper;
import com.hang.common.mapper.FoPlateMapper;
import com.hang.common.mapper.FoPlatePageMapper;
import com.hang.common.utils.AchieveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * @ClassName FoPlateService
 * @Author hang
 * @DATE 2018/11/14 14:57
 * @Description TODO
 **/
@Service
public class FoPlateService {

    @Autowired
    FoPlateMapper plateMapper;
    @Autowired
    FoPlatePageMapper foPlatePageMapper;
    @Autowired
    FoInfoMapper foInfoMapper;

    /**
     * 新增板块-操作，检查板块是否存在
     * */
    public boolean insertSelective(FoPlate foPlate){
        FoPlate useone = plateMapper.selectByName(foPlate.getName());
        if(useone != null){
            return false;
        }else{
            foPlate.setCtime(AchieveUtil.getDateTime(""));
            plateMapper.insertSelective(foPlate);
            return true;
        }
    }

    /**
     * 板块列表
     * @param foPlate 得到对应地址栏的参数的对象
     * */
    public List<FoPlate> selectByList(FoPlate foPlate){
        if (foPlate.getPage() != null && foPlate.getRows() != null) {
            PageHelper.startPage(foPlate.getPage(), foPlate.getRows());
        }
        List<FoPlate> fmr = plateMapper.selectByList(foPlate.getSearch());
        return fmr;
    }

    /**
     * 板块
     * @param id 得到对应地址栏的参数的对象
     * */
    public FoPlate selectByPrimaryKey(Integer id){
        FoPlate foPlate = plateMapper.selectByPrimaryKey(id);
        return foPlate;
    }

    /**
     * 编辑
     * */
    public int updateByPrimaryKeySelective(FoPlate foPlate){
        return plateMapper.updateByPrimaryKeySelective(foPlate);
    }

    public List<FoPlate> selectPlatePageList(){
        List<FoPlate> plates = plateMapper.selectByList("");
        plates.forEach(v->{
            v.setPageList(foPlatePageMapper.selectByPlateId(v.getId()));
        });
        plates.sort(Comparator.comparing(FoPlate::getSort));
        return plates;
    }

    public void deleteByPrimaryKey(FoPlate foPlate){
        plateMapper.deleteByPrimaryKey(foPlate.getId());
    }

    public FoInfo getInfo(){
        return foInfoMapper.getInfo();
    }

    public int upInfo(FoInfo foInfo){
        return foInfoMapper.updateByPrimaryKeySelective(foInfo);
    }
}
