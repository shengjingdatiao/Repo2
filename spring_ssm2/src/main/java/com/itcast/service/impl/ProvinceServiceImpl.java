package com.itcast.service.impl;

import com.itcast.mapper.ProvinceMapper;
import com.itcast.pojo.Province;
import com.itcast.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("province")
public class ProvinceServiceImpl implements ProvinceService {
@Autowired
private ProvinceMapper provinceMapper;
    @Override
    public List<Province> findAll() {
        return provinceMapper.findAll();
    }
}
