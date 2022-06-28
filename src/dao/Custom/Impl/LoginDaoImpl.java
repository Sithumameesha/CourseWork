package dao.Custom.Impl;

import dao.Custom.LoginDao;
import entity.Login;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utill.FactoryConfiguration;

import java.sql.SQLException;

public class LoginDaoImpl implements LoginDao {
    @Override
    public Login exist( String password) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

//        Query query = session.createQuery("select e from Login e where e.password = ?1 and e.name= ?2");
//        Login logins = (Login) query.uniqueResult();
//        transaction.commit();
//        session.close();
//        return logins;

        Login login = session.get(Login.class,password);
        transaction.commit();
        session.close();
        return login;
    }
}
