import dao.template.StudentDao;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import pojo.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by chengh on 2018/12/1.
 */
public class JDBCTemplateTest {

    class MyRowMapper implements RowMapper<Student> {
        /**
         * 获取查询的结果集，赋值给student对象 并返回
         *
         * @param resultSet 结果集
         * @param i
         * @return Student对象
         * @throws SQLException
         */
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student = new Student();
            student.setName(resultSet.getString("name"));
            student.setId(resultSet.getLong("id"));
            return student;
        }
    }

    /**
     * 一、JdbcTemplate使用的基本步骤
     */
    @Test
    public void test1() {

        //创建数据源
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/xz_1");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        //创建JdbcTemplate对象，设置数据源
        JdbcTemplate template = new JdbcTemplate(dataSource);

        //查询并返回结果
        List<Student> list = template.query("select * from student", new MyRowMapper());
        int count = (Integer) template.queryForObject("select count(1) from student", Integer.class);

        //断言 数据不为空
        Assert.assertTrue(list.size() > 0);
        Assert.assertTrue(count > 0);

        //遍历集合，输出结果
        for (Student student : list) {
            System.out.println(student.getId() + "/" + student.getName());
        }

    }

    /**
     * 二、通过 spring 配置文件的方式查询数据
     */
    @Test
    public void test2() {
        //加载配置
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/template/bean.xml");

        //从配置中获取 StudentDao 对象
        StudentDao studentDao = (StudentDao) context.getBean("StudentDao");

        //调用student的查询方法
        List<Student> list = studentDao.quyerAll();

        //断言查询的数据不为空
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

}
