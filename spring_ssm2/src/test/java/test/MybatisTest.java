package test;

import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource;
import com.itcast.mapper.ProvinceMapper;
import com.itcast.mapper.UserMapper;
import com.itcast.pojo.Province;
import com.itcast.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisTest {

    private UserMapper mapper;
    private InputStream is;
    private SqlSession sqlSession;
    private ProvinceMapper provinceMapper;

    @Before
    public void before() throws IOException {
        is = Resources.getResourceAsStream("sqlMapConfig-spring.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
        sqlSession = build.openSession(true);
        mapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void after() throws IOException {
        sqlSession.close();
         is.close();
    }


    @Test
    public void test2() {
        List<Province> all = provinceMapper.findAll();
        for (Province province : all) {
            System.out.println(province);
        }
    }
}
