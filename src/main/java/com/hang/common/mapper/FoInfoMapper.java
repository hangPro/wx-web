package com.hang.common.mapper;

import com.hang.common.domain.FoInfo;

public interface FoInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FoInfo record);

    int insertSelective(FoInfo record);

    FoInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FoInfo record);

    FoInfo getInfo();
}