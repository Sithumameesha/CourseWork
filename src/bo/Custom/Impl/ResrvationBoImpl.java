package bo.Custom.Impl;

import bo.Custom.ResrvationBo;
import dao.Custom.Impl.RoomDaoImpl;
import dao.Custom.Impl.StudentDaoImpl;
import dto.RoomDto;
import dto.StudentDto;
import entity.Room;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class ResrvationBoImpl implements ResrvationBo {
    RoomDaoImpl roomDao = new RoomDaoImpl();
    StudentDaoImpl studentDao= new StudentDaoImpl();
    @Override
    public ArrayList<RoomDto> loadAllRooms() throws SQLException, ClassNotFoundException {
        ArrayList<Room>all = roomDao.getAll();
        ArrayList<RoomDto> allRooms = new ArrayList<>();
        for (Room room: all){
            allRooms.add(new RoomDto(room.getRoom_type_id(),room.getType(),room.getKey_money(),room.getQty()));
        }
        return allRooms;
    }

    @Override
    public ArrayList<StudentDto> loadAllStudents() throws SQLException, ClassNotFoundException {
        ArrayList<Student>all = studentDao.getAll();
        ArrayList<StudentDto> allStudents = new ArrayList<>();
        for (Student student: all){
            allStudents.add(new StudentDto(student.getStudent_id(),student.getName(),student.getAddress(),student.getDob(),student.getGender(),student.getContact_no()));
        }
        return allStudents;
    }

    @Override
    public RoomDto searchRoom(String id) throws SQLException, ClassNotFoundException {
        Room all = roomDao.search(id);
      //  ArrayList <RoomDto>allRooms = new ArrayList<>();
       return new RoomDto(all.getRoom_type_id(),all.getType(),all.getKey_money(),all.getQty());
        //return allRooms;
    }


}
