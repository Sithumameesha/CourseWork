package dao.Custom;

import entity.Login;

import java.sql.SQLException;

public interface LoginDao {
    Login exist( String password) throws SQLException, ClassNotFoundException ;
}
