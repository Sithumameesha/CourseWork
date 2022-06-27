package dao.Custom;

import entity.Reservation;
import entity.Room;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationDao {
    boolean Save(Reservation reservation) throws Exception;

    boolean update(Reservation reservation) throws Exception;

    boolean delete(String id) throws Exception;

    ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException;
}
