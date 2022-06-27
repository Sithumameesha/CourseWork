package controller;

import bo.Custom.Impl.ResrvationBoImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ReservationDto;
import dto.RoomDto;
import dto.StudentDto;
import entity.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.TM.ResevationTm;
import view.TM.StudentsTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegFormController {
    public JFXTextField txtQty;
    public JFXTextField txtKeyMoney;
    public Label lblDate;
    public JFXTextField txtStatus;
    ResrvationBoImpl resrvationBo = new ResrvationBoImpl();

    public JFXComboBox <String>comStudentId;
    public JFXTextField txtRoomType;
    public Button btnNew;
    public AnchorPane root;
    public JFXTextField txtRes_Id;
    public JFXTextField txtStudentId;
    public Button btnAdd;
    public TableView tblReg;
    public Button btnDelete;
    public JFXComboBox <String>comRoomType;




    public void initialize() {
        loadAllRoomTypeId();
        loadAllStudentId();


        comRoomType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newRoomId) -> {
           // btnReserve.setDisable(newRoomId == null);


            if (newRoomId != null) {
                try {
//                    if (!exitRooms(newRoomId + "")) {
//
//                    }
                    RoomDto room = resrvationBo.searchRoom(newRoomId + "");
                    txtRoomType.setText(room.getType());
                    txtQty.setText(String.valueOf(room.getQty()));
                    txtKeyMoney.setText(room.getKey_money());

                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

            } else {
//                txtMonthlyRent.clear();
//                txtQty.clear();
//                txtStudentName.clear();
//                txtMonthlyRent.clear();
            }
        });
    }





    public void NewStudentOnAction(ActionEvent actionEvent) throws IOException {
        URL resource =getClass().getResource("../view/NewStudentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws Exception {
       String type_Id = comRoomType.getId();
        Object student_Id=comStudentId.getId();
        String date =lblDate.getText();
        String key_money = txtKeyMoney.getText();
        String status = txtStatus.getText();
        Object resId = txtRes_Id.getText();

        if (btnAdd.getText().equalsIgnoreCase("save")) {
            try {
                resrvationBo.SaveRes(new ReservationDto(type_Id, date, student_Id, resId, key_money, status));

                //  tblReg.getItems().add(new ResevationTm(type_Id,date,student_Id,key_money,status,resId));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to Save the Student " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }

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
    private void loadAllStudentId() {
        try {
            ArrayList<StudentDto> all = resrvationBo.loadAllStudents();
            for (StudentDto studentDto :all){
                comStudentId.getItems().add(studentDto.getStudent_id());
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load customer ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
