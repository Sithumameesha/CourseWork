package bo.Custom;

import entity.Login;

import java.sql.SQLException;

public interface LoginBo {
    Boolean exist( String password,String name) throws SQLException, ClassNotFoundException ;
}
