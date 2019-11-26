package test;

import com.itcast.mapper.ProvinceMapper;
import com.itcast.pojo.Province;
import com.itcast.pojo.User;
import com.itcast.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringTest {
    @Autowired
    private UserService userService;
    @Autowired
    private ProvinceMapper provinceMapper;
    @Test
    public void test(){
        List<Province> all = provinceMapper.findAll();
        for (Province province : all) {

            System.out.println(province);
        }
    }

}
