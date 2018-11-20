package com.hang.common.service;

import com.hang.common.domain.FoFunction;
import com.hang.common.mapper.FoFunctionMapper;
import com.hang.common.utils.AchieveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoFunctionService {

    @Autowired
    FoFunctionMapper foFunctionMapper;

    /**
     * 根据fid返回对应功能列表
     * @param fid 父ID
     * @param state 0为正常，1为隐藏
     * @param idin 子功能id,id...
     * @param fidin 父功能id,id...
     * */
    public List<FoFunction> selectByfid(Integer fid,boolean state,List<FoFunction> idin,List<FoFunction> fidin){
        return foFunctionMapper.selectByfid(fid,state,idin,fidin);
    }

    /**
     * 根据ID查询一条信息
     * */
    public FoFunction selectByPrimaryKey(Integer id){
        return foFunctionMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据Furi查询一条信息
     * */
    public FoFunction selectByFuri(String furi){
        return foFunctionMapper.selectByFuri(furi);
    }

    /**
     * 根据关键字like查询 @ 搜索子功能（fid<>0 为子功能） 根据关键字
     * */
    public List<FoFunction> selectByfnameLike(String search){
        return foFunctionMapper.selectByfnameLike(search);
    }

    /**
     * 通过子功能ID，查询父功能ID，ID
     * */
    public List<FoFunction> selectByinchildId(List<FoFunction> cctList){
        return foFunctionMapper.selectByinchildId(cctList);
    }

    /**
     * 根据fid查询该fid下面的子功能数量
     * */
    public Integer selectCidCount(Integer fid){
        return foFunctionMapper.selectCidCount(fid);
    }

    /**
     * 添加功能列表
     * */
    public int insertSelective(FoFunction foFunc){
        Integer fid = foFunc.getFid();
        if(fid == 0){ //新增功能组
            FoFunction foFunction = new FoFunction();
            foFunction.setFid(fid);
            foFunction.setFname(foFunc.getFidname());
            foFunction.setFuri("none");
            foFunction.setCtime(AchieveUtil.getDateTime(""));
            foFunctionMapper.insertSelective(foFunction);
            fid = foFunction.getId();
        }
        FoFunction zoFunction = new FoFunction();
        zoFunction.setFid(fid);
        zoFunction.setFname(foFunc.getFname());
        zoFunction.setFuri(foFunc.getFuri());
        zoFunction.setCtime(AchieveUtil.getDateTime(""));
        return foFunctionMapper.insertSelective(zoFunction);
    }

    /**
     * 编辑功能列表
     * */
    public int updateByPrimaryKeySelective(FoFunction foFunction){
        return foFunctionMapper.updateByPrimaryKeySelective(foFunction);
    }

    /**
     * 删除功能列表
     * */
    public int deleteByPrimaryKey(Integer id){
        return foFunctionMapper.deleteByPrimaryKey(id);
    }
}
