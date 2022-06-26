package controller;

import bo.Custom.Impl.StudentsBoImpl;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDto;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.TM.StudentsTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class NewStudentFormController {
    public TableView <StudentsTm>tblStudents;
    public Button btnDelete;
    StudentsBoImpl studentsBo= new StudentsBoImpl();
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

                tblStudents.getItems().add(new StudentsTm(id, name, address,con,dob,gender));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to Save the Student " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } else {

            try {
                studentsBo.Update(new StudentDto(id, name, address,con,dob,gender));


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

            tblStudents.getItems().remove(tblStudents.getSelectionModel().getSelectedItem());
            tblStudents.getSelectionModel().clearSelection();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the customer " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
