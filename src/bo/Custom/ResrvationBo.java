package bo.Custom;

import dto.RoomDto;
import dto.StudentDto;
import entity.Room;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ResrvationBo {
    ArrayList<RoomDto> loadAllRooms() throws SQLException, ClassNotFoundException ;
    ArrayList<StudentDto> loadAllStudents() throws SQLException, ClassNotFoundException ;
    RoomDto searchRoom(String id ) throws SQLException, ClassNotFoundException;

}
