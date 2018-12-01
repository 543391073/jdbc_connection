package dao.template;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import pojo.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by chengh on 2018/12/1.
 */
public class StudentDao {
    private JdbcTemplate template;

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public List<Student> quyerAll(){
        return template.query("select * from student", new MyRowMapper());
    }

    class MyRowMapper implements RowMapper<Student> {
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student = new Student();
            student.setName(resultSet.getString("name"));
            student.setId(resultSet.getLong("id"));
            return student;
        }
    }
}
