package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.PasswordField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.TimeUnit;
import java.sql.SQLException;

public class UpdateUserPageController extends MainFrameController {
    @FXML
    private TextField inputFirstName,inputLastName,inputUserID,inputUserName;

    @FXML
    private PasswordField inputPassWord;
    
    @FXML
    private Button btnSave,btnCancel;
    
    @FXML
    //public Label labelUsername;

    public void initialize() {
//    	System.out.println("UPDATE USER");
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	inputUserID.setText(String.valueOf(MainFrameController.usID));
    	inputUserName.setText(MainFrameController.userName);
    	inputFirstName.setText(MainFrameController.firstName);
    	inputLastName.setText(MainFrameController.lastName);
    	inputPassWord.setText(MainFrameController.passWord);
    	
    }
        
    @FXML
    void callUpdateUserFunc(ActionEvent event) {
//    	add validation
    	Connection con = DatabaseManager.getConnection();
		
//		String insertNewUser = "UPDATE useraccount(username,password,firstname,lastname) VALUES (?,?,?,?)";
		String updateUser = "UPDATE useraccount SET username = ?,password = ?,firstname = ?,lastname = ? WHERE userID = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(updateUser);

			stmt.setString(1, inputUserName.getText());
			stmt.setString(2, inputPassWord.getText());
			stmt.setString(3, inputFirstName.getText());
			stmt.setString(4, inputLastName.getText());
			stmt.setString(5, inputUserID.getText());
			
			stmt.executeUpdate();
	    	Stage stage = (Stage) btnSave.getScene().getWindow();
	    	stage.close();
		} catch (SQLException e) {
			e.printStackTrace();
//			errorMesage.setText("Error in Database");
		}
		
		//nextScene(btnSave,"MainFrame.fxml");
		//rfreshScene();
		//MainFrameController.initialize();
		
		//initialize();
		//this.labelUsername.setText(inputFirstName.getText());
		
		//nextScene(btnSave,"Mainframe.fxml");
		try {
		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainFrame.fxml"));
		    Parent root1 = (Parent) fxmlLoader.load();
		    Stage stage = new Stage();
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.initStyle(StageStyle.UNDECORATED);
		    //stage.setTitle("");
		    stage.setMaximized(true);
		    stage.setScene(new Scene(root1));  
		    stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void callCancelFunc(ActionEvent event) {
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
    	stage.close();
    }
    void rfreshScene() {
//    	Stage stage = MainFrameController.getScene.getWindow();
//    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MainFrame.fxml"));
//    	Stage stage1 = new Stage();
//    	Parent root1;
//		try {
//			root1 = (Parent) loader.load();
//			
//			stage1.initModality(Modality.APPLICATION_MODAL);
//		    stage1.initStyle(StageStyle.UNDECORATED);
//		    stage1.setTitle("ABC");
//		    stage1.setScene(new Scene(root1));  
//		    stage1.show();
//		    //stage.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    
    }

}
