package controller;

import bo.Custom.Impl.RoomBoImpl;
import com.jfoenix.controls.JFXTextField;
import dto.RoomDto;
import dto.StudentDto;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.TM.RoomTm;
import view.TM.StudentsTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class NewRoomController {
RoomBoImpl roomBo = new RoomBoImpl();
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
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to Save the Room " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            try {
                roomBo.Update(new RoomDto(roomId, type, keyMoney, qty));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the Room " + roomId + e.getMessage()).show();
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

            tblRooms.getItems().remove(tblRooms.getSelectionModel().getSelectedItem());
            tblRooms.getSelectionModel().clearSelection();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the Room " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
