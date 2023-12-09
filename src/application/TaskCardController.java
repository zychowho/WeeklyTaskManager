package application;

import java.awt.TextArea;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import com.gluonhq.charm.glisten.control.DropdownButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import model.Task;

public class TaskCardController{

    
    //private DropdownButton DpbtnStatus;
	@FXML
    private VBox Box;

	@FXML
    public AnchorPane ShowPane;
	
	 @FXML
	private Button btnDeleteData;
	 
	 
    @FXML
    private Label labelDate,labelTime;

    @FXML
    private Text labelDescription;
    
    private String msg;

    @FXML
    private Label labelTitle,lableID,labelUsername,labelStatus;

//    private String [] colors = { "ece0d1","dbc1ac","C8B6A6"};
    public static String str;
    
    private String[] taskID;
    
    
    public void setData(Task task) {
//    	System.out.println(task.getTitle());
//    	System.out.println(task.getDescription());
//    	System.out.println(task.getTaskDate());
    	
    	//msg = task.getDescription();
    	lableID.setText(task.getTaskID());
    	labelTitle.setText(task.getTitle());
    	labelTime.setText(task.getTime());
    	
    	//System.out.println("test status"+task.getStatus());
    	
    	labelStatus.setText(task.getStatus());
    	
    	
    	
    	//labelDescription.setText(msg);
    	labelDescription.setText(task.getDescription());
    	//labelDescription.setText(task.getDescription());
    	//task.getStatus();
    	//labelDescription.getText(task.getDescription());
    	//labelDescription.setText("Leo");
    	//str = task.getDescription();
    	
    	labelDate.setText(task.getTaskDate());
    	Box.setStyle("-fx-background-color : #F4EAE0;"+ 
    			"-fx-background-radius : 15;" + "-fx-margin : 20 0 0 20;");
    	
    }
    
    void test() 
    {
    	
    }
    
    @FXML
    void CallFuncUpdate(ActionEvent event) {
    	System.out.println("btn Click   : " + lableID.getText());
    	taskID = new String[] {lableID.getText(),labelTitle.getText(),labelDescription.getText(),labelDate.getText(),labelTime.getText(),labelStatus.getText()};
    	 //taskID = lableID.getText();
    	try {
		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddTaskPage.fxml"));
		    Parent root1 = (Parent) fxmlLoader.load();
		    Stage stage = new Stage();
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.initStyle(StageStyle.UNDECORATED);
		    AddTaskPageController controller = fxmlLoader.getController();
		    controller.setDataToUpdate(taskID);
//		    stage.setTitle("ABC");
		    stage.setScene(new Scene(root1));  
		    stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
    	
//    	FXMLLoader fxmlLoader = new FXMLLoader();
//		fxmlLoader.setLocation(getClass().getResource("TaskCard.fxml"));
//		VBox cardBox = fxmlLoader.load();
//		TaskCardController cardcontroller = fxmlLoader.getController();
//		cardcontroller.setData(fentchDataTaskPerUserMonday.get(i));
//		//VboxMonday.getChildren().clear();
//		VboxMonday.getChildren().add(cardBox);

    }
    
    @FXML
    void CallFuncDelete(ActionEvent event) {
    		
    		System.out.println("btn Click   : " + lableID.getText());
    		
    		//System.out.println(labelUsername.getText());
    		
    		Connection conn = DatabaseManager.getConnection();

    		String getUserInfo = "DELETE FROM `tasklist` WHERE taskID = ?";
    		try {
    			PreparedStatement stmt = conn.prepareStatement(getUserInfo);
    			stmt.setString(1, lableID.getText());
    			
    			stmt.executeUpdate();
    		}
    		catch (SQLException e)
    		{
    			
    		}
    		
    		
//			try {
//				 
//				 AnchorPane pane = FXMLLoader.load(getClass().getResource("WeeklyViewPage.fxml"));
//				ShowPane.getChildren().setAll(pane);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
         	
    		
//    		int year = 0, month = 0, day = 0;
//        	LocalDate localDate = LocalDate.now();
//        	String cdate = localDate.toString();
//        	 
//        	String[] arrLastDate = 	cdate.split("-");
//        	
//        	year = Integer.valueOf(arrLastDate[0]);
//        	month = Integer.valueOf(arrLastDate[1]);
//        	day = Integer.valueOf(arrLastDate[2]);
//        	//WeeklyViewPageController.GetFullWeekDate(year, month, day);
        	
        	//CallFuncSearchWeek();
        	
//    		FXMLLoader fxmlLoader = new FXMLLoader();
//    		fxmlLoader.setLocation(TaskCardController.class.getResource("WeeklyViewPage.fxml"));
////    		fxmlLoader.setLocation(getClass().getResource("TaskCard.fxml"));
////    		VBox cardBox = fxmlLoader.load();
//    		WeeklyViewPageController cardcontroller = fxmlLoader.getController();
//    		//WeeklyViewPageController cardcontroller = WeeklyViewPageController.
//    		
//    		cardcontroller.GetTaskFromFullWeek(year, month, day);
    		//GetTaskFromFullWeek(year,month,day);
//    		VboxSunday.getChildren().clear();
    		//VboxSunday.getChildren().add(cardBox);
        	//CallFuncSearchWeek();
        	
//        	try {
//				MainFrameController.ShowPane.getChildren().clear();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
    }
    
}
