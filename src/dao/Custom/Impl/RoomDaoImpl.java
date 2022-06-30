package dao.Custom.Impl;

import dao.Custom.RoomDao;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utill.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDaoImpl implements RoomDao {
    @Override
    public boolean Save(Room room) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(room);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room room) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(room);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room= session.load(Room.class,id);

        session.delete(room);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Room> getAll() throws SQLException, ClassNotFoundException {
            ArrayList<Room> allRooms;
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("from Room ");

            allRooms = (ArrayList<Room>)query.list();


            transaction.commit();
            session.close();
            return allRooms;
    }

    @Override
    public Room search(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.get(Room.class, id);
        transaction.commit();
        session.close();
        return room;


    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("SELECT room_type_id FROM Room ORDER  BY room_type_id DESC LIMIT 1");
        String s = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        if (s != null) {
            int newStudentId = Integer.parseInt(s.replace("REG", "")) + 1;
            return String.format("REG%03d", newStudentId);
        }
        return "REG001";
    }
}

