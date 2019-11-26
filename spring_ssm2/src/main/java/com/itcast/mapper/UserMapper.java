package com.itcast.mapper;



import com.itcast.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {
    //查询所有
    List<User> findAll(User paramUser);

    //添加数据
    void add(User user);

    //删除数据
    void delete(int id);

    //查询一个
    User findById(@Param("id")int id);

    //修改数据
    void update(User user);

    //批量删除
    void deleteSelect(int[] uid);


}
