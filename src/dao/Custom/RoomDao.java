package dao.Custom;

import entity.Room;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomDao {
    boolean Save(Room room) throws Exception;

    boolean update(Room room) throws Exception;

    boolean delete(String id) throws Exception;

    ArrayList<Room> getAll() throws SQLException, ClassNotFoundException;
}
