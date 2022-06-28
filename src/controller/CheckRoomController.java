package controller;

import bo.BoFactory;
import bo.Custom.Impl.RoomBoImpl;
import dto.RoomDto;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class CheckRoomController {
    RoomBoImpl roomBo= (RoomBoImpl) BoFactory.boFactory().getBO(BoFactory.BOTypes.ROOM);
    public AnchorPane root;
    public TableView <RoomTm>tblRoomAvailable;
    public ImageView homePng;





    public void initialize() throws Exception {

        tblRoomAvailable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        tblRoomAvailable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblRoomAvailable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("key_money"));
        tblRoomAvailable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        LoadRooms();
    }

    private void LoadRooms() {
        tblRoomAvailable.getItems().clear();
        try {
            ArrayList<RoomDto> allRooms = roomBo.getAllRooms();
            for (RoomDto roomDto : allRooms) {
                tblRoomAvailable.getItems().add(new RoomTm(roomDto.getRoom_type_id(),roomDto.getType(),roomDto.getKey_money(),roomDto.getQty()));
                tblRoomAvailable.refresh();
            }
        } catch (SQLException e) {
        } catch (ClassNotFoundException e) {

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
}
