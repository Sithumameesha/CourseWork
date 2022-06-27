package bo.Custom.Impl;

import bo.Custom.ResrvationBo;
import dao.Custom.Impl.RoomDaoImpl;
import dto.RoomDto;
import entity.Room;

import java.sql.SQLException;
import java.util.ArrayList;

public class ResrvationBoImpl implements ResrvationBo {
    RoomDaoImpl roomDao = new RoomDaoImpl();
    @Override
    public ArrayList<RoomDto> loadAllRooms() throws SQLException, ClassNotFoundException {
        ArrayList<Room>all = roomDao.getAll();
        ArrayList<RoomDto> allRooms = new ArrayList<>();
        for (Room room: all){
            allRooms.add(new RoomDto(room.getRoom_type_id(),room.getType(),room.getKey_money(),room.getQty()));
        }
        return allRooms;
    }
}
