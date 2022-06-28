package bo.Custom.Impl;

import bo.Custom.LoginBo;
import dao.Custom.Impl.LoginDaoImpl;
import dao.Custom.LoginDao;
import entity.Login;

import java.sql.SQLException;

public class LoginBoImpl implements LoginBo {
LoginDao loginDao= new LoginDaoImpl();


    @Override
    public Boolean exist( String password,String name) throws ClassNotFoundException, SQLException {
       return loginDao.exist( password,name);
    }
}
