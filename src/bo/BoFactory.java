package bo;

import bo.Custom.Impl.ResrvationBoImpl;
import bo.Custom.Impl.RoomBoImpl;
import bo.Custom.Impl.StudentsBoImpl;
import bo.Custom.StudentBo;

public class BoFactory {
    private static BoFactory boFactory;

    public BoFactory() {
    }

    public static BoFactory boFactory() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;

    }
    public enum BOTypes {
        RESERVATION,ROOM,STUDENT
    }
    public SuperBo getBO(BOTypes Types) {
        switch (Types) {
            case RESERVATION:
                return new ResrvationBoImpl();
            case ROOM:
                return new RoomBoImpl();
            case STUDENT:
                return new StudentsBoImpl();
            default:
                return null;
        }

    }
}
