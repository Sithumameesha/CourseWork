package controller;

import bo.BoFactory;
import bo.Custom.Impl.StudentsBoImpl;
import com.jfoenix.controls.JFXTextField;
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
import view.TM.StudentsTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class NewStudentFormController {
    public TableView <StudentsTm>tblStudents;
    public Button btnDelete;
    public ImageView HomePng;
    public AnchorPane root;

    StudentsBoImpl studentsBo= (StudentsBoImpl) BoFactory.boFactory().getBO(BoFactory.BOTypes.STUDENT);
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtDob;
    public JFXTextField txtCon;
    public JFXTextField txtAddress;
    public JFXTextField txtGender;
    public Button btnAdd;


    public void initialize() throws Exception {
        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("student_id"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudents.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblStudents.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblStudents.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("contact_no"));

      tblStudents.refresh();

        tblStudents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnAdd.setText(newValue != null ? "Update" : "Save");
            btnAdd.setDisable(newValue == null);

            if (newValue != null) {
             txtId.setText(newValue.getStudent_id());
             txtName.setText(newValue.getName());
             txtAddress.setText(newValue.getAddress());
             txtDob.setText(newValue.getDob());
             txtCon.setText(newValue.getContact_no());
             txtGender.setText(newValue.getGender());
            }
        });
        LoadAllStudents();
        tblStudents.refresh();
        }


    public void AddOnAction(ActionEvent actionEvent) throws Exception {
        tblStudents.refresh();
    String id = txtId.getText();
    String name= txtName.getText();
    String address = txtAddress.getText();
    String con= txtCon.getText();
    String dob= txtDob.getText();
    String gender = txtGender.getText();

        if (btnAdd.getText().equalsIgnoreCase("save")) {
            try {
                studentsBo.Save(new StudentDto(id, name, address,con,dob,gender));
                new Alert(Alert.AlertType.CONFIRMATION, " Save the Student " ).show();
                tblStudents.getItems().add(new StudentsTm(id, name, address,con,dob,gender));
                ClearText();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to Save the Student " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } else {

            try {
                studentsBo.Update(new StudentDto(id, name, address,con,dob,gender));
                new Alert(Alert.AlertType.CONFIRMATION, "Updated " ).show();
                ClearText();

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the Student " + id + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            StudentsTm selectedStudent = tblStudents.getSelectionModel().getSelectedItem();
            selectedStudent.setName(name);
            selectedStudent.setStudent_id(id);
            selectedStudent.setAddress(address);
            selectedStudent.setDob(dob);
            selectedStudent.setContact_no(con);
            selectedStudent.setGender(gender);
            tblStudents.refresh();
        }

    }
    public void LoadAllStudents(){
        tblStudents.getItems().clear();
        try {
            ArrayList<StudentDto> allStudents = studentsBo.getAllStudents();
            for (StudentDto student : allStudents) {
                tblStudents.getItems().add(new StudentsTm(student.getStudent_id(),student.getName(),student.getAddress(),student.getDob(),student.getGender(),student.getContact_no()));
                System.out.println(student.getStudent_id());
                tblStudents.refresh();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void DleteOnAction(ActionEvent actionEvent) throws Exception {


        String id = tblStudents.getSelectionModel().getSelectedItem().getStudent_id();
        try {
            studentsBo.delete(id);

            new Alert(Alert.AlertType.CONFIRMATION, " Deleted t " ).show();
            tblStudents.getItems().remove(tblStudents.getSelectionModel().getSelectedItem());
            tblStudents.getSelectionModel().clearSelection();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the customer " ).show();
            ClearText();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void ClearText() {
        txtGender.clear();
        txtCon.clear();
        txtDob.clear();
        txtAddress.clear();
        txtName.clear();
        txtId.clear();
    }

    public void ClickOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource =getClass().getResource("../view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void ExitedOnAction(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
        }
    }

    public void PlayOnAction(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch (icon.getId()) {
                case "HomePng":
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

    public void newOnAction(ActionEvent actionEvent) {
        ClearText();
    }
}
