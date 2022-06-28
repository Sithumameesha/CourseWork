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
    Login login=null;
    @Override
    public boolean exist(String password,String name) throws SQLException, ClassNotFoundException {
       try {
           Session session = FactoryConfiguration.getInstance().getSession();
           Transaction transaction = session.beginTransaction();
               login = (Login) session.createQuery("select e from Login e where e.password = :password and e.name = :name").setParameter("password", password).setParameter("").uniqueResult();
               if (login != null && login.getPassword().equals(password)) {
                   return true;
       }

            transaction.commit();
            session.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;




    }
}
