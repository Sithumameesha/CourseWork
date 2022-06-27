package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class RegFormController {
    public Button btnNew;
    public AnchorPane root;
    public JFXTextField txtRes_Id;
    public JFXTextField txtStudentId;
    public Button btnAdd;
    public TableView tblReg;
    public Button btnDelete;
    public JFXComboBox comRoomType;
    public JFXComboBox comKeyMoney;

    public void NewStudentOnAction(ActionEvent actionEvent) throws IOException {
        URL resource =getClass().getResource("../view/NewStudentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void btnAddOnAction(ActionEvent actionEvent) {

    }

    public void DeletOnAction(ActionEvent actionEvent) {

    }
}
