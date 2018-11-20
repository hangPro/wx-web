package com.hang.common.mapper;

import com.hang.common.domain.FoPlatePage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoPlatePageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FoPlatePage record);

    int insertSelective(FoPlatePage record);

    FoPlatePage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FoPlatePage record);

    int updateByPrimaryKey(FoPlatePage record);

    FoPlatePage selectByName(String name);

    List<FoPlatePage> selectByList(@Param("search") String search);

    List<FoPlatePage> selectByPlateId(@Param("plateId") Integer plateId);
}