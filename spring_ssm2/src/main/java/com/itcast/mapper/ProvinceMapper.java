package com.itcast.mapper;

import com.itcast.pojo.Province;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProvinceMapper {
    /*  @Select("select * from province where code = #{code}")*/
    Province findByCode(String code);

    //查询所有
    List<Province> findAll();

}
