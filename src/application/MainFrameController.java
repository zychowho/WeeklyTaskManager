package application;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import javafx.stage.PopupWindow;
import javafx.stage.StageStyle;

public class MainFrameController extends LoginPageController {

//    @FXML
//    private Button btnClose;


    @FXML
    public AnchorPane ShowPane;
    
    @FXML
    private Button btnLogout;
    
    @FXML
    private Button btnAddTask;

//    @FXML
//    private Button btnMinimize;

    @FXML
    private Button btnUpdateUser, btnWeelkyView;

    @FXML
    public Label labelUsername;
    
    public static String userName,firstName,lastName,passWord;

    
    
    public void initialize() throws Exception {
		fetchDataUser();
		labelUsername.setText("@" + firstName);
		showInAnchorPane("WeeklyViewPage.fxml");
	}
    
    public void fetchDataUser() {
    	Connection conn = DatabaseManager.getConnection();

		String getUserInfo = "SELECT * FROM useraccount WHERE userID = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(getUserInfo);
			stmt.setString(1, String.valueOf(usID));
			
			ResultSet result = stmt.executeQuery();
			result.next();
				this.userName = result.getString("username");
				this.passWord = result.getString("password");
				this.firstName = result.getString("firstname");
				this.lastName = result.getString("lastname");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NO DB CONNECTION");
		}
		
    }

    @FXML
    void callLogoutFunc(ActionEvent event) {
    	Stage stage = (Stage) btnLogout.getScene().getWindow();
    	stage.close();
		try {
		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
		    Parent root1 = (Parent) fxmlLoader.load();
		    Stage stages = new Stage();
		    stages.initModality(Modality.APPLICATION_MODAL);
		    stages.initStyle(StageStyle.UNDECORATED);
//		    stages.setTitle("ABC");
		    stages.setScene(new Scene(root1));  
		    stages.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}	

    }

    @FXML
    void callUpdateUserFunc(ActionEvent event) throws Exception {
    	//newFormPopUp();
    	showInAnchorPane("UpdateUserPage.fxml");
		//Scene scene = new Scene(root);
    }
    
    @FXML
    void callMonthlyViewFunc(ActionEvent event) throws Exception {
    	showInAnchorPane("MonthlyViewPage.fxml");
    }
    
    @FXML
    void callWeeklyViewFunc(ActionEvent event) throws Exception {
    	showInAnchorPane("WeeklyViewPage.fxml");
    }
    
     void showInAnchorPane(String fxmlName) throws Exception
     {
    	 AnchorPane pane  = FXMLLoader.load(getClass().getResource(fxmlName));
     	ShowPane.getChildren().setAll(pane);
     }
    
    public void newFormPopUp() {
    	btnUpdateUser.setOnMouseClicked(event ->{
    		try {
    		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateUserPage.fxml"));
    		    Parent root1 = (Parent) fxmlLoader.load();
    		    Stage stage = new Stage();
    		    stage.initModality(Modality.APPLICATION_MODAL);
    		    stage.initStyle(StageStyle.UNDECORATED);
//    		    stage.setTitle("ABC");
    		    stage.setScene(new Scene(root1));  
    		    stage.show();
    		}
    		catch (IOException e) {
				e.printStackTrace();
			}
            
		});
    }

	
//    @FXML
//	public void minimizeFunc(ActionEvent event) {
//		Stage stage = (Stage) btnMinimize.getScene().getWindow();
//		stage.setIconified(true);
//	}
//    
//    @FXML
//    public void closeAppFunc(ActionEvent event) {
//    	Stage stage = (Stage) btnClose.getScene().getWindow();
//    	stage.close(); 
//    }

    @FXML
    void callAddTaskFunc(MouseEvent event) {
		try {
		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddTaskPage.fxml"));
		    Parent root1 = (Parent) fxmlLoader.load();
		    Stage stage = new Stage();
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.initStyle(StageStyle.UNDECORATED);
//		    stage.setTitle("ABC");
		    stage.setScene(new Scene(root1));  
		    stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
    }
	

}