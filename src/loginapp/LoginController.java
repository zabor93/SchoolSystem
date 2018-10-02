package loginapp;

import admin.AdminController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import student.StudentController;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    LoginModel loginModel = new LoginModel();

    @FXML
    private Label dbstatus;

    @FXML
    private javafx.scene.control.TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<option> comboBox;

    @FXML
    private javafx.scene.control.Button loginButton;

    @FXML
    private Label loginStatus;


    public void initialize(URL url, ResourceBundle rb) {

        if (this.loginModel.isDataBaseConnected()) {
            this.dbstatus.setText("Connected to database");

        } else {
            this.dbstatus.setText("Not COnnected to database");
        }
        this.comboBox.setItems(FXCollections.observableArrayList(option.values()));

    }

    @FXML
    public void Login(ActionEvent event) {

        try {
            if (this.loginModel.isLogin(this.username.getText(), this.password.getText(), ((option) this.comboBox.getValue()).toString())) {
                Stage stage = (Stage) this.loginButton.getScene().getWindow();
                stage.close();
                switch (((option) this.comboBox.getValue()).toString()) {
                    case "Admin":
                        adminLogin();
                        break;
                    case "Student":
                        studentLogin();
                        break;
                }
            } else {
                this.loginStatus.setText("Wrong Credentials");
            }

        } catch (Exception localException) {
        }
    }

    public void studentLogin() {
        try {
            Stage userstage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/student/studentFXML.fxml").openStream());

            StudentController studentController = (StudentController) loader.getController();

            Scene scene = new Scene(root);
            userstage.setScene(scene);
            userstage.setTitle("Student DASHBOARD");
            userstage.setResizable(false);
            userstage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void adminLogin() {
        try {
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminroot = (Pane) adminLoader.load(getClass().getResource("/admin/Admin.fxml").openStream());
            AdminController adminController = (AdminController) adminLoader.getController();

            Scene scene = new Scene(adminroot);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin DASHBOARD");
            adminStage.setResizable(false);
            adminStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
