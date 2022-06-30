package dao.Custom.Impl;

import dao.Custom.StudentDAO;
import dto.StudentDto;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utill.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDAO {
    @Override
    public boolean Save(Student student) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student student) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.load(Student.class,id);

        session.delete(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
      ArrayList<Student> allStudent;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Student ");

        allStudent = (ArrayList<Student>)query.list();


        transaction.commit();
        session.close();
        return allStudent;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("SELECT student_id FROM Student ORDER  BY student_id DESC LIMIT 1");
        String s = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        if (s != null) {
            int newStudentId = Integer.parseInt(s.replace("S-", "")) + 1;
            return String.format("S-%03d", newStudentId);
        }
        return "S-001";
    }
}
