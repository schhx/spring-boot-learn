package org.schhx.springbootlearn.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.schhx.springbootlearn.model.User;
import org.schhx.springbootlearn.model.UserExample;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    List<User> selectByExample(UserExample example);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);
}