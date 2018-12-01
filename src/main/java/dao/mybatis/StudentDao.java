package dao.mybatis;

import pojo.Student;

import java.util.List;

/**
 * Created by chengh on 2018/12/1.
 */
public interface StudentDao {
    List<Student> queryAll();
}
