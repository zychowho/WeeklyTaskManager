package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.*;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class SignUpPageController extends LoginPageController{

    @FXML
    private Button btnClose;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSubmit;
    
    private Label errorMesage;

    @FXML
    private TextField inputFirstname;

    @FXML
    private TextField inputLastname;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private TextField inputUsername;
	
    public void signUpData() {
    	 
    	if(!inputUsername.getText().isBlank() && !inputFirstname.getText().isBlank() && !inputLastname.getText().isBlank() && !inputPassword.getText().isBlank()) 
    	{
    		Connection con = DatabaseManager.getConnection();
    		
    		String insertNewUser = "INSERT INTO useraccount(username,password,firstname,lastname) VALUES (?,?,?,?)";
    		try {
    			PreparedStatement stmt = con.prepareStatement(insertNewUser);
 
				stmt.setString(1, inputUsername.getText());
				stmt.setString(2, inputPassword.getText());
				stmt.setString(3, inputFirstname.getText());
				stmt.setString(4, inputLastname.getText());
    			
				stmt.executeUpdate();
				
					//errorMesage.setText("Login Successful!");
					
					try {
						TimeUnit.SECONDS.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					this.nextScene(btnSubmit,"LoginPage.fxml"); //Should open the mainframe.fxml
			} catch (SQLException e) {
				e.printStackTrace();
//				errorMesage.setText("Error in Database");
				//errorMesage.setText("DB error");
			}
    		
    	}
    	else {
//			errorMesage.setText("Please Complete the fields!");
    	
		}
    	
    }
	@FXML
	public void closeApp(ActionEvent event) {
    	Stage stage = (Stage) btnClose.getScene().getWindow();
    	stage.close();
	}
	
    @FXML
   void callLoginFuncbtn(ActionEvent event) {
    	this.nextScene(btnLogin,"LoginPage.fxml");
    }
	
    @FXML
    void submitSignupFunc(ActionEvent event) {
    	signUpData();
    }
    
}
