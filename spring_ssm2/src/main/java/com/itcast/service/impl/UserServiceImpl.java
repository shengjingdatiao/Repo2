package com.itcast.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itcast.doamain.PageBean;
import com.itcast.mapper.UserMapper;
import com.itcast.pojo.User;
import com.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public PageBean<User> findAll(Integer currentPage, Integer pageSize,User user)  {
        //设置分页参数
        PageHelper.startPage(currentPage, pageSize);
        //对查询参数进行处理
        User paramUser = new User();
        if (user.getName()!=null && user.getName().length()>0){
            paramUser.setName("%"+user.getName()+"%");
        }
         if (user.getEmail()!=null && user.getEmail().length()>0){
             paramUser.setEmail("%"+user.getEmail()+"%");
         }
        //进行分页查询
        Page<User> all = (Page<User>) userMapper.findAll(paramUser);
        //封装分页数据
        PageBean<User> PageBean = new PageBean<>();
        PageBean.setCurrentPage(all.getPageNum());
        PageBean.setPageSize(all.getPageSize());
        PageBean.setTotalCount(Long.valueOf(all.getTotal()).intValue());
        PageBean.setList(all.getResult());
        PageBean.setTotalPage(all.getPages());
        return PageBean;
    }

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public void delete(int id) {
        if (id>0){
            userMapper.delete(id);
        }

    }

    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void deleteSelect(int[] uid) {
        userMapper.deleteSelect(uid);
    }
}
