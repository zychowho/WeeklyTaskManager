package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class SignUpPageController {

    @FXML
    private Button btnClose;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField inputFirstname;

    @FXML
    private TextField inputLastname;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private TextField inputUsername;
	
	@FXML
	public void closeApp(ActionEvent event) {
    	Stage stage = (Stage) btnClose.getScene().getWindow();
    	stage.close();
	}
	
    @FXML
    void callLoginFunc(ActionEvent event) {
//    	pag niclick yung sign-upbtn dapat lalabas yung LoginPage.fxml
    }
	
    @FXML
    void submitSignupFunc(ActionEvent event) {
//    	dapat makakapacreate sya ng acc at masasave sa DB
//    	dapat pag may account mareredirect sa login screen	

    }
}
