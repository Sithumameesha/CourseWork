package dao.Custom;

import entity.Login;

import java.sql.SQLException;

public interface LoginDao {
    boolean exist( String password,String name) throws SQLException, ClassNotFoundException ;
}
