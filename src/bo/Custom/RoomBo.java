package bo.Custom;

import dto.RoomDto;
import dto.StudentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomBo {
    boolean Save(RoomDto roomDto) throws Exception;
    ArrayList<RoomDto > getAllRooms() throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws Exception;
    boolean Update(RoomDto roomDto) throws Exception;
}
