package dto;

import entity.Reservation;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class RoomDto {
    private String room_type_id;
    private String type;
    private String key_money;
    private int qty;
    private List<Reservation> roomlist = new ArrayList<>();

    public RoomDto(String room_type_id, String type, String key_money, int qty, List<Reservation> roomlist) {
        this.room_type_id = room_type_id;
        this.type = type;
        this.key_money = key_money;
        this.qty = qty;
        this.roomlist = roomlist;
    }

    public RoomDto() {
    }

    public String getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(String room_type_id) {
        this.room_type_id = room_type_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey_money() {
        return key_money;
    }

    public void setKey_money(String key_money) {
        this.key_money = key_money;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public List<Reservation> getRoomlist() {
        return roomlist;
    }

    public void setRoomlist(List<Reservation> roomlist) {
        this.roomlist = roomlist;
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "room_type_id='" + room_type_id + '\'' +
                ", type='" + type + '\'' +
                ", key_money='" + key_money + '\'' +
                ", qty=" + qty +
                ", roomlist=" + roomlist +
                '}';
    }
}
