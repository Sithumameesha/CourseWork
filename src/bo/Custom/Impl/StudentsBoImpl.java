package bo.Custom.Impl;

import bo.Custom.StudentBo;
import dao.Custom.Impl.StudentDaoImpl;
import dto.StudentDto;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentsBoImpl implements StudentBo {
StudentDaoImpl studentDao = new StudentDaoImpl();
    @Override
    public boolean Save(StudentDto studentdto) throws Exception {
        return studentDao.Save(new Student(
            studentdto.getStudent_id(),
                studentdto.getName(),
                studentdto.getAddress(),
                studentdto.getDob(),
                studentdto.getGender(),
                studentdto.getContact_no()
        ));
    }

    @Override
    public ArrayList<StudentDto> getAllStudents() throws SQLException, ClassNotFoundException {
        ArrayList<Student>all = studentDao.getAll();
        ArrayList<StudentDto> allStudents = new ArrayList<>();
        for (Student student: all){
            allStudents.add(new StudentDto(student.getStudent_id(),student.getName(),student.getAddress(),student.getDob(),student.getGender(),student.getContact_no()));
        }
        return allStudents;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return studentDao.delete(id);
    }

    @Override
    public boolean Update(StudentDto studentdto) throws Exception {
        return studentDao.update(new Student(
                studentdto.getStudent_id(),
                studentdto.getName(),
                studentdto.getAddress(),
                studentdto.getDob(),
                studentdto.getGender(),
                studentdto.getContact_no()

        ));
    }
}
