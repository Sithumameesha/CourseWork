package dto;

import entity.Reservation;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDto {
    private String student_id;
    private String name;
    private String address;
    private String dob;
    private String gender;
    private String contact_no;


    public StudentDto() {
    }



    public StudentDto(String student_id, String name, String address, String dob, String gender, String contact_no) {
        this.student_id = student_id;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.contact_no = contact_no;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "student_id='" + student_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", contact_no='" + contact_no + '\'' +
                '}';
    }
}
