package controller;

import bo.BoFactory;
import bo.Custom.Impl.ResrvationBoImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ReservationDto;
import dto.RoomDto;
import dto.StudentDto;
import entity.Student;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.TM.ResevationTm;
import view.TM.StudentsTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RegFormController {
    public JFXTextField txtQty;
    public JFXTextField txtKeyMoney;
    public Label lblDate;
    public JFXTextField txtStatus;
    public ImageView homepng;
    ResrvationBoImpl resrvationBo = (ResrvationBoImpl) BoFactory.boFactory().getBO(BoFactory.BOTypes.RESERVATION);

    public JFXComboBox<String> comStudentId;
    public JFXTextField txtRoomType;
    public Button btnNew;
    public AnchorPane root;
    public JFXTextField txtRes_Id;
    public Button btnAdd;
    public TableView <ResevationTm>tblReg;
    public Button btnDelete;
    public JFXComboBox<String> comRoomType;


    public void initialize() {
        loadAllRoomTypeId();
        loadAllStudentId();
        //loadAllRes();
        tblReg.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("res_id"));
        tblReg.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblReg.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("student_id"));
        tblReg.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        tblReg.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("status"));
        tblReg.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("key_Money"));


        comRoomType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newRoomId) -> {
            // btnReserve.setDisable(newRoomId == null);


            if (newRoomId != null) {
                try {
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

    private void loadAllRes() {
        tblReg.getItems().clear();
        try {
            ArrayList<ReservationDto> allRes = resrvationBo.allRes();
            for (ReservationDto reservationDto : allRes) {
                tblReg.getItems().add(new ResevationTm(reservationDto.getStudent_id(), reservationDto.getDate(),reservationDto.getStudent_id(),reservationDto.getRoom_type_id(),reservationDto.getKey_Money(),reservationDto.getStatus()));
                tblReg.refresh();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }


    public void NewStudentOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/NewStudentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws Exception {
        String type_Id = comRoomType.getValue();
        String student_Id = comStudentId.getValue();
        String date = lblDate.getText();
        String key_money = txtKeyMoney.getText();
        String status = txtStatus.getText();
        String resId = txtRes_Id.getText();


        boolean b = saveReserve(resId, date, student_Id, type_Id, key_money, status);

        if (b) {

            new Alert(Alert.AlertType.CONFIRMATION, "Save " ).show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Not Save " ).show();

        }


    }



    public boolean saveReserve(String resId, String date,String student_Id, String type_Id, String key_money,String states) throws Exception {
        try {
            resrvationBo.SaveRes(new ReservationDto(resId, date,student_Id,type_Id,  key_money, states));
            tblReg.getItems().add(new ResevationTm(resId, date, student_Id, type_Id, key_money, states));
        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void DeletOnAction(ActionEvent actionEvent) throws Exception {
        String id = tblReg.getSelectionModel().getSelectedItem().getRes_id();
        try {

            resrvationBo.deleteRes(id);
            new Alert(Alert.AlertType.ERROR, "Deleted").show();

            tblReg.getItems().remove(tblReg.getSelectionModel().getSelectedItem());
            tblReg.getSelectionModel().clearSelection();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete  " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadAllRes();

    }

    private void loadAllRoomTypeId() {
        try {
            ArrayList<RoomDto> all = resrvationBo.loadAllRooms();
            for (RoomDto roomDto : all) {
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
            for (StudentDto studentDto : all) {
                comStudentId.getItems().add(studentDto.getStudent_id());
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load customer ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clickOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource =getClass().getResource("../view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void Exiet(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
        }
    }

    public void EnterOnAction(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch (icon.getId()) {
                case "homepng":
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);

        }
    }
}
