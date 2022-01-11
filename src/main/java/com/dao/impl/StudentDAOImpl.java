package com.dao.impl;

import com.dao.StudentDAO;
import com.model.Student;
import com.model.StudentMapper;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDAOImpl implements StudentDAO, DisposableBean {

    @Autowired
    public DataSource dataSource;
    public JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void createTableIfNotExit(){
        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
            ResultSet rs = dbmd.getTables(null, null, "STUDENT", null);
            if (rs.next()){
                System.out.println("Table is already exit!");
                return;
            }
            jdbcTemplate.execute("CREATE TABLE STUDENT (" +
                    "id int primary key identity, " +
                    "name varchar(255) not null unique," +
                    "age int not null )");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insert(Student student) {
        jdbcTemplate.update("INSERT INTO STUDENT (name, age) VALUES (?, ?)", student.getName(), student.getAge());
    }

    @Override
    public List<Student> list() {
        List<Student> list = new ArrayList<>();
        try {
            String query = "Select * from STUDENT";
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                Student student = new Student();
                student.setId(result.getInt("id"));
                student.setName(result.getString("name"));
                student.setAge(String.valueOf(result.getInt("age")));
                list.add(student);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteStudent(int id) {
        jdbcTemplate.execute("delete from STUDENT where id = " + id);
    }

    @Override
    public Student get(int id) {
        return jdbcTemplate.queryForObject("select * from STUDENT where id = ?", new StudentMapper(), id);
    }

    @Override
    public void update(Student student) {
        jdbcTemplate.update("update STUDENT set name = ? where id = ? ", student.getName() , student.getId());
    }


    @Override
    public void destroy() throws Exception {

    }
}
