package bo.Custom;

import bo.SuperBo;
import dto.ReservationDto;
import dto.RoomDto;
import dto.StudentDto;
import entity.Room;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ResrvationBo extends SuperBo {
    ArrayList<RoomDto> loadAllRooms() throws SQLException, ClassNotFoundException ;
    ArrayList<StudentDto> loadAllStudents() throws SQLException, ClassNotFoundException ;
    RoomDto searchRoom(String id ) throws SQLException, ClassNotFoundException;

    boolean SaveRes(ReservationDto reservationDto) throws Exception;
    ArrayList<ReservationDto >allRes() throws SQLException, ClassNotFoundException;
    boolean deleteRes(String id) throws Exception;
    boolean Update(ReservationDto reservationDto) throws Exception;
}
