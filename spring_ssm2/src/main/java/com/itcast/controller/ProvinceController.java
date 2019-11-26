package com.itcast.controller;

import com.itcast.pojo.Province;
import com.itcast.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<Province> findAll() {
        return provinceService.findAll();
    }
}
