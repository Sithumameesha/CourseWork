package dao.Custom;

import dto.StudentDto;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentDAO {
    boolean Save(Student student) throws Exception;

    boolean update(Student student) throws Exception;

    boolean delete(String id) throws Exception;

    ArrayList<Student> getAll() throws SQLException, ClassNotFoundException;
}
