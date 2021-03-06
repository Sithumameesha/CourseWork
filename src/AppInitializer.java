import entity.Login;
import entity.Room;
import entity.Student;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utill.FactoryConfiguration;

import java.io.IOException;
import java.util.List;

public class AppInitializer extends Application {

    public static void main(String[] args) {launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("view/MainPage.fxml"))));
        primaryStage.setTitle("WholeSale Management");
        primaryStage.centerOnScreen();
        primaryStage.show();
//        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("view/DashBoard.fxml"))));
//        primaryStage.setTitle("WholeSale Management");
//        primaryStage.centerOnScreen();
//        primaryStage.show();
    }
}
