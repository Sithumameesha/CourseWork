package controller;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class DashBoardController {
    public ImageView regIcon;
    public ImageView roomsIcon;
    public ImageView findIcons;
    public AnchorPane root;
    public Label lblMenu;
    public Label lblDescription;

    public void regClickOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource =getClass().getResource("../view/RegForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void roomClickOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource =getClass().getResource("../view/newRoom.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void EnterOnAction(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch (icon.getId()) {
                case "regIcon":
                    lblMenu.setText("Reservation");
                    lblDescription.setText("Click to Add Student,Reserve the Room");
                    break;
                case "findIcons":
                    lblMenu.setText("Manage Rooms");
                    lblDescription.setText("Click to add new Room, delete or view items");
                    break;
                case "roomsIcon":

                    lblMenu.setText("Room Availablity");
                    lblDescription.setText("Click to view Available Room");
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

    public void ExitedOnAction(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("Welcome");
            lblDescription.setText("Please select one of above main operations to proceed");
        }
    }

    public void Check(MouseEvent mouseEvent) throws IOException {
        URL resource =getClass().getResource("../view/CheckRoom.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(new Scene(load));

    }
}
