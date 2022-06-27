package bo.Custom;

import dto.RoomDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ResrvationBo {
    ArrayList<RoomDto> loadAllRooms() throws SQLException, ClassNotFoundException ;
}
