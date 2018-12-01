import dao.mybatis.StudentDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.junit.Assert;
import org.junit.Test;
import pojo.Student;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * Created by chengh on 2018/12/1.
 */
public class MybatisTest {
    @Test
    public void test() {
        String resource = "mybatis-conf.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        StudentDao studentDao = session.getMapper(StudentDao.class);
        List<Student> list = studentDao.queryAll();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }
}
