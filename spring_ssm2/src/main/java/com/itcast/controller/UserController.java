package com.itcast.controller;

import com.itcast.doamain.PageBean;
import com.itcast.pojo.User;
import com.itcast.service.UserService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public String findAll(Model model,
                          @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                          User user
                          ) throws UnsupportedEncodingException {

        PageBean<User> pageBean = userService.findAll(currentPage, pageSize,user);

        model.addAttribute("pageBean", pageBean);
        return "list";
    }

    @RequestMapping("/addUI")
    public String addUI() {
        return "add";
    }

    @RequestMapping("/save")
    public String save(User user, HttpServletRequest request) {
        userService.add(user);
        return "redirect:" + request.getContextPath() + "/user/findAll";
    }

    @RequestMapping("/delete")
    public String delete(int id, HttpServletRequest request) {
        userService.delete(id);
        return "redirect:" + request.getContextPath() + "/user/findAll";
    }

    @RequestMapping("/findById")
    public String findById(int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user",user);
        return "update";
    }
    @RequestMapping("/update")
    public String update(User user,HttpServletRequest request){
        userService.update(user);
        return "redirect:" + request.getContextPath() + "/user/findAll";
    }
    @RequestMapping("/deleteSelect")
    public String deleteSelect(int[] uid, HttpServletRequest request){
        userService.deleteSelect(uid);
        return "redirect:" + request.getContextPath() + "/user/findAll";
    }
}
