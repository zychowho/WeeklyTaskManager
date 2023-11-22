package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
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

    public void initialize() {
//    	System.out.println("UPDATE USER");
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
	    	Stage stage = (Stage) btnCancel.getScene().getWindow();
	    	stage.close();
		} catch (SQLException e) {
			e.printStackTrace();
//			errorMesage.setText("Error in Database");
		}
    }
    
    @FXML
    void callCancelFunc(ActionEvent event) {
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
    	stage.close();
    }
}
