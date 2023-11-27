package application;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import model.Task;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class WeeklyViewPageController extends MainFrameController {

    @FXML
    private Button btnClose;

    @FXML
    private Button btnMinimize;
    
    @FXML
    private VBox VboxSunday;
    
    private List<Task> fentchDataTaskPerUserSunday;

    @FXML
    void closeAppFunc(ActionEvent event) {

    }

    @FXML
    void minimizeFunc(ActionEvent event) {

    }
    
    
    public void initialize() {
    	
    	ShowTask();
    	System.out.println(usID);
    	//fentchDataTaskPerUser();
    	fentchDataTaskPerUserSunday =  new ArrayList<>(fentchDataTaskPerUserSunday());
    	
    	System.out.println(fentchDataTaskPerUserSunday.size());
    	
    	try {
	    	for (int i = 0 ; i<fentchDataTaskPerUserSunday.size(); i++) 
	    		{
		    		FXMLLoader fxmlLoader = new FXMLLoader();
		    		fxmlLoader.setLocation(getClass().getResource("TaskCard.fxml"));
		    		VBox cardBox = fxmlLoader.load();
		    		TaskCardController cardcontroller = fxmlLoader.getController();
		    		cardcontroller.setData(fentchDataTaskPerUserSunday.get(i));
		    		VboxSunday.getChildren().add(cardBox);
				} 
    		
    	}
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    void ShowTask() {
    	
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
    	   //LocalDateTime now = LocalDateTime.now();  
    	LocalDateTime now = LocalDateTime.now();
    	   System.out.println(dtf.format(now)); 
    	   
    	  //String dateToday =  date.set(2023,Calendar.NOVEMBER,27);
    	  
    	   
    	   Date date = new Date();
           Calendar c = Calendar.getInstance();
           c.setTime(date);
           int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
           System.out.println("Day of week in number:"+dayOfWeek);
           String dayWeekText = new SimpleDateFormat("EEEE").format(date);
           System.out.println("Day of week in text:"+dayWeekText);
    	   
           
           
           
           
    	       }
    
    
    private List<Task> fentchDataTaskPerUserSunday() {
    	
    	List<Task> ls = new ArrayList<>();
    	
    	Connection conn = DatabaseManager.getConnection();

		String getUserInfo = "SELECT * FROM tasklist WHERE userID = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(getUserInfo);
			stmt.setString(1, String.valueOf(usID));
			
			//ResultSet result = stmt.executeQuery();
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) 
			{
				//System.out.println(result.getString("Title"));
				 Task task = new Task();
				 task.setTitle(result.getString("Title"));
				 task.setDescription(result.getString("Description"));
				 task.setTaskDate(result.getString("Date"));
				 task.setStatus(result.getString("Status"));
				 ls.add(task);
				 
			}
			//result.next();
			//result.getRow();
			
//			ResultSetMetaData rss =	result.getMetaData();
//			
//			int count = rss.g
			
//				this.userName = result.getString("username");
//				this.passWord = result.getString("password");
//				this.firstName = result.getString("firstname");
//				this.lastName = result.getString("lastname");
			
			
			
			//System.out.println(result.getRow().count());
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NO DB CONNECTION");
		}
		
		return ls;
    }
}
