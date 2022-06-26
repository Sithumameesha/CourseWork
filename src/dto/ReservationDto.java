package dto;

import entity.Room;
import entity.Student;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

public class ReservationDto {
    private String res_id;
    private String date;
    private Student student_id;
    private Room room_type_id;
    private String status;
    private String key_Money;

    public ReservationDto() {
    }

    public ReservationDto(String res_id, String date, Student student_id, Room room_type_id, String status, String key_Money) {
        this.res_id = res_id;
        this.date = date;
        this.student_id = student_id;
        this.room_type_id = room_type_id;
        this.status = status;
        this.key_Money = key_Money;
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Student getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Student student_id) {
        this.student_id = student_id;
    }

    public Room getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(Room room_type_id) {
        this.room_type_id = room_type_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey_Money() {
        return key_Money;
    }

    public void setKey_Money(String key_Money) {
        this.key_Money = key_Money;
    }

    @Override
    public String toString() {
        return "ReservationDto{" +
                "res_id='" + res_id + '\'' +
                ", date='" + date + '\'' +
                ", student_id=" + student_id +
                ", room_type_id=" + room_type_id +
                ", status='" + status + '\'' +
                ", key_Money='" + key_Money + '\'' +
                '}';
    }
}
