package dao.Custom;

import dao.SuperDao;
import entity.Room;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomDao extends SuperDao {
    boolean Save(Room room) throws Exception;

    boolean update(Room room) throws Exception;

    boolean delete(String id) throws Exception;

    ArrayList<Room> getAll() throws SQLException, ClassNotFoundException;

     Room search (String id ) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;
}
