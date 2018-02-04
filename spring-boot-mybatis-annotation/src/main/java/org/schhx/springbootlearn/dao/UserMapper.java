package org.schhx.springbootlearn.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.schhx.springbootlearn.module.User;

public interface UserMapper {

    @Insert("insert into user (id, username, age) values(#{id}, #{username}, #{age})")
    int insert(User record);

    @Delete("delete from user where id = #{id}")
    int deleteByPrimaryKey(String id);

    @Update("update user set username = #{username}, age = #{age} where id = #{id}")
    int updateByPrimaryKey(User record);

    @Select("select * from user where id = #{id}")
    User selectByPrimaryKey(String id);
}