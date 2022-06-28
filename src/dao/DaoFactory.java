package dao;

import dao.Custom.Impl.ReservationDaoImpl;
import dao.Custom.Impl.RoomDaoImpl;
import dao.Custom.Impl.StudentDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;

    }

    public enum DAOTypes {

        RESERVATION,ROOM,STUDENT

    }

    public SuperDao getDAO(DAOTypes Types) {
        switch (Types) {
            case RESERVATION:
                return new ReservationDaoImpl();
            case ROOM:
                return new RoomDaoImpl();
            case STUDENT:
                return new StudentDaoImpl();

            default:
                return null;
        }
    }
}
