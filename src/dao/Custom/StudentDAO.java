package dao.Custom;

import dao.SuperDao;
import dto.StudentDto;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentDAO extends SuperDao {
    boolean Save(Student student) throws Exception;

    boolean update(Student student) throws Exception;

    boolean delete(String id) throws Exception;

    ArrayList<Student> getAll() throws SQLException, ClassNotFoundException;
}
