package loginapp;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    LoginModel loginModel=new LoginModel();

    @FXML
    private Label dbstatus;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<option> comboBox;

    @FXML
    private Button loginButton;


    public void initialize(URL url, ResourceBundle rb) {

        if (this.loginModel.isDataBaseConnected()) {
            this.dbstatus.setText("Connected");

        }
        else{
            this.dbstatus.setText("Not COnnected to database");
        }

        this.comboBox.setItems(FXCollections.observableArrayList(option.values()));

    }
}
