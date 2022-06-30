package bo.Custom;

import bo.SuperBo;
import dto.RoomDto;
import dto.StudentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomBo extends SuperBo {
    boolean Save(RoomDto roomDto) throws Exception;
    ArrayList<RoomDto > getAllRooms() throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws Exception;
    boolean Update(RoomDto roomDto) throws Exception;
    ArrayList<RoomDto> SearchRoom(String id) throws SQLException, ClassNotFoundException;
    String generateNewRoomId() throws SQLException, ClassNotFoundException;
}
