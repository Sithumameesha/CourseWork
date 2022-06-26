package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashBoardController {
    public ImageView regIcon;
    public ImageView roomsIcon;
    public ImageView findIcons;
    public AnchorPane root;

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
}
