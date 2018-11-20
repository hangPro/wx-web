package com.hang.common.mapper;

import com.hang.common.domain.FoPlate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoPlateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FoPlate record);

    int insertSelective(FoPlate record);

    FoPlate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FoPlate record);

    int updateByPrimaryKey(FoPlate record);

    FoPlate selectByName(String name);

    List<FoPlate> selectByList(@Param("search") String search);
}