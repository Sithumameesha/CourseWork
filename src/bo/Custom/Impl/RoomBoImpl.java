package bo.Custom.Impl;

import bo.Custom.RoomBo;
import dao.Custom.Impl.RoomDaoImpl;
import dto.RoomDto;
import dto.StudentDto;
import entity.Room;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomBoImpl implements RoomBo {
  RoomDaoImpl roomDao = new RoomDaoImpl();
    @Override
    public boolean Save(RoomDto roomDto) throws Exception {
         return roomDao.Save(new Room(roomDto.getRoom_type_id(),roomDto.getType(),roomDto.getKey_money(),roomDto.getQty()));
    }

    @Override
    public ArrayList<RoomDto> getAllRooms() throws SQLException, ClassNotFoundException {
        ArrayList<Room>all = roomDao.getAll();
        ArrayList<RoomDto> allRooms = new ArrayList<>();
        for (Room room: all){
            allRooms.add(new RoomDto(room.getRoom_type_id(),room.getType(),room.getKey_money(),room.getQty()));
        }
        return allRooms;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return roomDao.delete(id);
    }

    @Override
    public boolean Update(RoomDto roomDto) throws Exception {
        return roomDao.update(new Room(roomDto.getRoom_type_id(),roomDto.getType(),roomDto.getKey_money(),roomDto.getQty()));
    }

    @Override
    public ArrayList<RoomDto> SearchRoom(String id) throws SQLException, ClassNotFoundException {
        Room all = roomDao.search(id);
        ArrayList <RoomDto>allRooms = new ArrayList<>();
        allRooms.add(new RoomDto(all.getRoom_type_id(),all.getType(),all.getKey_money(),all.getQty()));
        return allRooms;
    }

    @Override
    public String generateNewRoomId() throws SQLException, ClassNotFoundException {
        return roomDao.generateNewID();
    }

}
