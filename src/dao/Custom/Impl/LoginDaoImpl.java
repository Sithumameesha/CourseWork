package dao.Custom.Impl;

import dao.Custom.LoginDao;
import entity.Login;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utill.FactoryConfiguration;

import java.sql.SQLException;

public class LoginDaoImpl implements LoginDao {
    @Override
    public Login exist(String name, String password) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("select e from Login e where e.password = ? and e.name= ?");
        Login logins = (Login) query.uniqueResult();
        transaction.commit();
        session.close();
        return logins;
    }
}
