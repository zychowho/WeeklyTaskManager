package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class LoginPageController {

    @FXML
    private Button btnClose , btnLogin;

    @FXML
    private Label errorMesage;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private TextField inputUsername;
	
	@FXML
	public void callLoginFunc(ActionEvent event) {
		if (!inputUsername.getText().isBlank() && !inputPassword.getText().isBlank()) {
			Connection connection = DatabaseManager.getConnection();

			String verifyLogin = "SELECT * FROM useraccount WHERE username = ? AND password = ?";
			try {
				PreparedStatement stmt = connection.prepareStatement(verifyLogin);
				stmt.setString(1, inputUsername.getText());
				stmt.setString(2, inputPassword.getText());

				ResultSet result = stmt.executeQuery();

				if (result.next()) {
					errorMesage.setText("Login Successful!");
					nextScene(); //Should open the mainframe.fxml
				} else {
					errorMesage.setText("Incorrect Credentials");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				errorMesage.setText("Error in Database");
			}
		} else {
			errorMesage.setText("Please input username or password!");
		}
		
		
	}
	
	public void nextScene() {
//		tawagin nya yung mainframe.fxml yun na dapat naka display after login
	}
	
    @FXML
    void callSignupFunc(ActionEvent event) {
//    	pag niclick yung sign-upbtn dapat lalabas yung SignUpPage.fxml
    }
	
    @FXML
    void closeApp(ActionEvent event) {
    	Stage stage = (Stage) btnClose.getScene().getWindow();
    	stage.close();
    }
}
