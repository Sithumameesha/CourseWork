package controller;

import bo.Custom.Impl.LoginBoImpl;
import bo.Custom.LoginBo;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entity.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class LoginPageController {
    LoginBo loginBo = new LoginBoImpl();
    public AnchorPane root;
    public JFXTextField txtName;
    public Button btnLogin;
    public JFXPasswordField txtPassword;

    public void LoginOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        String name = txtName.getText();
        String password= txtPassword.getText();



        if (txtPassword == null){
            Login exist = loginBo.exist( password);
            new Alert(Alert.AlertType.CONFIRMATION, "Please").show();
//        URL resource =getClass().getResource("../view/DashBoard.fxml");
//            Parent load = FXMLLoader.load(resource);
//            Stage window = (Stage) root.getScene().getWindow();
//            window.setScene(new Scene(load));

        }else {
            new Alert(Alert.AlertType.ERROR, "Fuck This").show();
        }
    }







        }



