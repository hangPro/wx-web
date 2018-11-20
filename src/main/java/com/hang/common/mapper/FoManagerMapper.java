package com.hang.common.mapper;

import com.hang.common.domain.FoManager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FoManager record);

    int insertSelective(FoManager record);

    FoManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FoManager record);

    int updateByPrimaryKey(FoManager record);

    //查询管理员总数
    int selectByCount();

    //根据用户名查询单条数据
    FoManager selectByUsername(@Param("username") String username, @Param("id") Integer id);

    //管理员列表
    List<FoManager> selectByList(@Param("groupId") Integer groupId, @Param("search") String search);


}