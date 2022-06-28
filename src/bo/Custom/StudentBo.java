package bo.Custom;

import bo.SuperBo;
import dto.StudentDto;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBo extends SuperBo {
    boolean Save(StudentDto studentdto) throws Exception;
    ArrayList<StudentDto > getAllStudents() throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws Exception;
    boolean Update(StudentDto studentdto) throws Exception;
}
