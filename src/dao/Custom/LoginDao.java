package dao.Custom;

import entity.Login;

import java.sql.SQLException;

public interface LoginDao {
    Login exist(String name, String password) throws SQLException, ClassNotFoundException ;
}
