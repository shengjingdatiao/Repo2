package com.itcast.service;

import com.itcast.doamain.PageBean;
import com.itcast.pojo.User;

import java.io.UnsupportedEncodingException;


public interface UserService {
    //查询所有
    PageBean<User> findAll(Integer currentPage, Integer rows,User user) throws UnsupportedEncodingException;

    //添加数据
    void add(User user);

    //删除数据
    void delete(int id);

    //查找一个
    User findById(int id);

    //修改数据
    void update(User user);

    //批量删除
    void deleteSelect(int[] uid);
}
