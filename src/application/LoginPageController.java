package application;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
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
    private Button btnClose,btnLogin,btnSignUp;

    @FXML
    private Label errorMesage;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private TextField inputUsername;
	
    int usID;
    
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
					
					try {
						TimeUnit.SECONDS.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					nextScene(btnLogin,"Mainframe.fxml"); //Should open the mainframe.fxml
					
					//System.out.println(result.getInt("userID")); // return userID
					
					var userId = result.getInt("userID"); // int
					//return String.valueOf(userId);
					userData(userId);
				} else {
					errorMesage.setText("Incorrect Credentials!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				errorMesage.setText("Error in Database");
			}
		} else {
			errorMesage.setText("Please input username or password!");
		}
		//return null;
		
		
	}
	
	public void nextScene(Button btn, String newFrom){
//		tawagin nya yung mainframe.fxml yun na dapat naka display after login
		
		//Button btnLogin = new Button();
		
		btn.setOnMouseClicked(event ->{
			FXMLLoader loader = new FXMLLoader(getClass().getResource(newFrom));
            Parent root = null;
				try {
					root = loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            Stage stage = (Stage) btn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
		});
		
		
		
	}
	
    @FXML
    void callSignupFunc(ActionEvent event) {
//    	pag niclick yung sign-upbtn dapat lalabas yung SignUpPage.fxml
    	nextScene(btnSignUp,"SignupPage.fxml");// Open signup frame
    	
    }
	
    @FXML
    void closeApp(ActionEvent event) {
    	Stage stage = (Stage) btnClose.getScene().getWindow();
    	stage.close();
    }
    
   public void userData(int userID){
	   
	   System.out.println(userID);
	//return userID;
	   this.usID = userID;
	   
   }
   
}
