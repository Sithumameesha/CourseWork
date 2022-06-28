package utill;

import entity.Login;
import entity.Reservation;
import entity.Room;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private static SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(Room.class).addAnnotatedClass(Login.class);


        sessionFactory = configuration.buildSessionFactory();

    }
    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration==null) ? new FactoryConfiguration() : factoryConfiguration;
    }
    public Session getSession(){
        return  sessionFactory.openSession();
    }
}
