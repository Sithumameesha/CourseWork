package bo.Custom.Impl;

import bo.Custom.ResrvationBo;
import dao.Custom.Impl.ReservationDaoImpl;
import dao.Custom.Impl.RoomDaoImpl;
import dao.Custom.Impl.StudentDaoImpl;
import dao.DaoFactory;
import dto.ReservationDto;
import dto.RoomDto;
import dto.StudentDto;
import entity.Reservation;
import entity.Room;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utill.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;

public class ResrvationBoImpl implements ResrvationBo {
    RoomDaoImpl roomDao= (RoomDaoImpl) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.ROOM);
    StudentDaoImpl studentDao= (StudentDaoImpl) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.STUDENT);
    ReservationDaoImpl reservationDao = (ReservationDaoImpl) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.RESERVATION);
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
       return new RoomDto(all.getRoom_type_id(),all.getType(),all.getKey_money(),all.getQty());

    }

    @Override
    public boolean SaveRes(ReservationDto dto) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, dto.getStudent_id());
        Room room = session.get(Room.class, dto.getRoom_type_id());

        Reservation reserve = new Reservation(dto.getRes_id(), dto.getDate(), student, room,dto.getKey_Money(), dto.getStatus());
        session.save(reserve);

        room.setQty(room.getQty() - 1);
        session.update(room);

        transaction.commit();
        session.close();
        return true;    }

    @Override
    public ArrayList<ReservationDto> allRes() throws SQLException, ClassNotFoundException {
        ArrayList<Reservation>all = reservationDao.getAll();
        ArrayList<ReservationDto> allRes = new ArrayList<>();
        for (Reservation reservation: all){
           allRes.add(new ReservationDto(reservation.getRes_id(),reservation.getDate(),reservation.getStudent_id().getStudent_id(),reservation.getRoom_type_id().getRoom_type_id(),reservation.getStatus(),reservation.getKey_Money()));
        }
        return allRes;
    }

    @Override
    public boolean deleteRes(String id) throws Exception {
        return reservationDao.delete(id);
    }

    @Override
    public boolean Update(ReservationDto reservationDto) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, reservationDto.getStudent_id());
        Room room = session.get(Room.class, reservationDto.getRoom_type_id());
        transaction.commit();
        session.close();
        return reservationDao.update(new Reservation(reservationDto.getRes_id(),reservationDto.getDate(),student,room,reservationDto.getKey_Money(),reservationDto.getKey_Money()));

    }

    @Override
    public String generateResId() throws SQLException, ClassNotFoundException {
        return reservationDao.generateNewID();
    }


}
