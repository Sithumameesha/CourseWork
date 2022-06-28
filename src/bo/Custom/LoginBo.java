package bo.Custom;

import entity.Login;

import java.sql.SQLException;

public interface LoginBo {
    Login exist( String password) throws SQLException, ClassNotFoundException ;
}
