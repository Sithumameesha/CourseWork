package controller;

import bo.Custom.Impl.ResrvationBoImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.RoomDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegFormController {
    public void initialize() {
        loadAllRoomTypeId();

    }
    ResrvationBoImpl resrvationBo = new ResrvationBoImpl();
    public Button btnNew;
    public AnchorPane root;
    public JFXTextField txtRes_Id;
    public JFXTextField txtStudentId;
    public Button btnAdd;
    public TableView tblReg;
    public Button btnDelete;
    public JFXComboBox <String>comRoomType;
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
    private void loadAllRoomTypeId() {
        try {


            ArrayList<RoomDto> all = resrvationBo.loadAllRooms();
            for (RoomDto roomDto :all){
                comRoomType.getItems().add(roomDto.getRoom_type_id());
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load customer ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
