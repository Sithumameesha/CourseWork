package controller;

import bo.BoFactory;
import bo.Custom.Impl.RoomBoImpl;
import com.jfoenix.controls.JFXTextField;
import dto.RoomDto;
import dto.StudentDto;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.TM.RoomTm;
import view.TM.StudentsTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewRoomController {
    public ImageView homePng;
    RoomBoImpl roomBo = (RoomBoImpl) BoFactory.boFactory().getBO(BoFactory.BOTypes.ROOM);
    public AnchorPane root;
    public JFXTextField txtRoomId;
    public JFXTextField txtType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public Button btnSave;
    public Button btnDelete;
    public TableView <RoomTm>tblRooms;

    public void initialize() throws Exception {

        tblRooms.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        tblRooms.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblRooms.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("key_money"));
        tblRooms.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSave.setText(newValue != null ? "Update" : "Save");
            if (newValue != null) {
                txtRoomId.setText(newValue.getRoom_type_id());
                txtKeyMoney.setText(newValue.getKey_money());
                txtQty.setText(String.valueOf(newValue.getQty()));
                txtType.setText(newValue.getType());

            }
        });
       LoadAllRooms();
       tblRooms.refresh();





    }

    private void LoadAllRooms() {
        tblRooms.getItems().clear();
        try {
            ArrayList<RoomDto> allRooms = roomBo.getAllRooms();
            for (RoomDto roomDto : allRooms) {
                tblRooms.getItems().add(new RoomTm(roomDto.getRoom_type_id(),roomDto.getType(),roomDto.getKey_money(),roomDto.getQty()));
                tblRooms.refresh();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void SaveOnAction(ActionEvent actionEvent) throws Exception {
        String roomId = txtRoomId.getText();
        String type = txtType.getText();
        String keyMoney = txtKeyMoney.getText();
        int qty = Integer.parseInt(txtQty.getText());
        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                roomBo.Save(new RoomDto(roomId, type, keyMoney, qty));
                tblRooms.getItems().add(new RoomTm(roomId, type, keyMoney, qty));
                new Alert(Alert.AlertType.CONFIRMATION,"Succesfully add New Room").show();
                ClearText();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,"Falied add New Room");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            try {
                roomBo.Update(new RoomDto(roomId, type, keyMoney, qty));
                ClearText();
                new Alert(Alert.AlertType.CONFIRMATION, " updated  " ).show();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, " Falied update  " ).show();
            } catch (ClassNotFoundException e) {

                e.printStackTrace();
            }

            RoomTm rm = tblRooms.getSelectionModel().getSelectedItem();
            rm.setRoom_type_id(roomId);
            rm.setKey_money(keyMoney);
            rm.setType(type);
            rm.setQty(qty);
            tblRooms.refresh();
        }


    }

    public void DeleteOnAntion(ActionEvent actionEvent) throws Exception {
        String id = tblRooms.getSelectionModel().getSelectedItem().getRoom_type_id();
        try {
            roomBo.delete(id);
            new Alert(Alert.AlertType.ERROR, " Deleted  " ).show();
            ClearText();

            tblRooms.getItems().remove(tblRooms.getSelectionModel().getSelectedItem());
            tblRooms.getSelectionModel().clearSelection();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the Room").show();
            ClearText();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Click(MouseEvent mouseEvent) throws IOException {
        URL resource =getClass().getResource("../view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void Enter(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch (icon.getId()) {
                case "homePng":
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

    public void Exited(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
        }
    }

    public void newOnaction(ActionEvent actionEvent) {
        ClearText();
    }

    private void ClearText() {
        txtType.clear();
        txtKeyMoney.clear();
        txtQty.clear();
        txtRoomId.clear();
        btnSave.setText("Save");
       // txtRoomId.setText(generateNewId());
    }
    private String generateNewId() {
        try {
            return roomBo.generateNewRoomId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (tblRooms.getItems().isEmpty()) {
            return "R-001";
        } else {
            String id = getLastCustomerId();
            int newCustomerId = Integer.parseInt(id.replace("C", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        }

    }
    private String getLastCustomerId() {
        List<RoomTm> tmList = new ArrayList<>(tblRooms.getItems());

        Collections.sort(tmList);
        return tmList.get(tmList.size() - 1).getRoom_type_id();
    }
}
