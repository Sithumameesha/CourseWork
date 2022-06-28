package controller;

import bo.Custom.Impl.LoginBoImpl;
import bo.Custom.LoginBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entity.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class MainPageController {
    public AnchorPane root;
    LoginBo loginBo = new LoginBoImpl();
    public JFXTextField txtUserName;
    public JFXButton btnLogin;
    public JFXPasswordField txtPassword;

    public void btnLoginOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        String password= txtPassword.getText();

        try {
            boolean d = loginBo.exist(password);
            if (!d){
                new Alert(Alert.AlertType.ERROR, " Password Wrong ").show();
                txtPassword.clear();
                txtUserName.clear();
            }else {
                URL resource =getClass().getResource("../view/DashBoard.fxml");
                Parent load = FXMLLoader.load(resource);
                Stage window = (Stage) root.getScene().getWindow();
                window.setScene(new Scene(load));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
