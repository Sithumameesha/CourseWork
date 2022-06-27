package dao.Custom.Impl;

import dao.Custom.ReservationDao;
import entity.Reservation;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utill.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDaoImpl implements ReservationDao {
    @Override
    public boolean Save(Reservation reservation) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(reservation);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reservation reservation) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(reservation);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Reservation reservation= session.load(Reservation.class,id);

        session.delete(reservation);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Reservation> allRes;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Reservation ");

        allRes = (ArrayList<Reservation>)query.list();


        transaction.commit();
        session.close();
        return allRes;
    }
}
