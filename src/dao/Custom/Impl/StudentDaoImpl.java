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
}
