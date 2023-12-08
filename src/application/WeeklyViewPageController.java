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
import java.time.temporal.ChronoField;
import java.time.temporal.IsoFields;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WeeklyViewPageController extends MainFrameController {

    @FXML
    private Button btnClose, btnTest;

    @FXML
    private Button btnSearchDateWeek;
    @FXML
    private Button btnMinimize;
    
    @FXML
    private DatePicker DTpickerBar;
    
    @FXML
    private Text labeldateShow;
    @FXML
    private VBox VboxSunday,VboxMonday,VboxTuesday,VboxWednesday,VboxThursday,VboxFriday,VboxSaturday;
    
    private List<Task> fentchDataTaskPerUserSunday;
    private List<Task> fentchDataTaskPerUserMonday;
    private List<Task> fentchDataTaskPerUserTuesday;
    private List<Task> fentchDataTaskPerUserWednesday;
    private List<Task> fentchDataTaskPerUserThursday;
    private List<Task> fentchDataTaskPerUserFriday;
    private List<Task> fentchDataTaskPerUserSaturday;
    
    //static TemporalAdjuster test  = TemporalAdjuster.ofDateAdjuster(date ->date(2));

    @FXML
	public void minimizeFunc(ActionEvent event) {
//		Stage stage = (Stage) btnMinimize.getScene().getWindow();
//		stage.setIconified(true);
	}
  
    @FXML
    public void closeAppFunc(ActionEvent event) {
//  	Stage stage = (Stage) btnClose.getScene().getWindow();
//  	stage.close();
  }
    
    public void initialize() {
    	
    	//ShowTask();
    	//DatePicker DTpickertest = new DatePicker();
    	//DTpickertest.ge
    	
    	
    	int year = 0, month = 0, day = 0;
    	LocalDate localDate = LocalDate.now();
    	String cdate = localDate.toString();
    	 
    	String[] arrLastDate = 	cdate.split("-");
    	
    	year = Integer.valueOf(arrLastDate[0]);
    	month = Integer.valueOf(arrLastDate[1]);
    	day = Integer.valueOf(arrLastDate[2]);
    	
    	GetTaskFromFullWeek(year,month,day);
    	
    	String[] dateGet = GetFullWeekDate(year, month, day);
    	
    	labeldateShow.setText(dateGet[0] +"     -     "+dateGet[6]);
    	
	}
    
    void GetTaskFromFullWeek(int year, int month, int day) {
    	//value of GetFullWeekDate is in int form GetFullWeekDate(int,int,int);
    	String[] datesToGetDatas = GetFullWeekDate(year,month,day); // -Sunday - Saturday
    	
    	fentchDataTaskPerUserSunday =  new ArrayList<>(fentchDataTaskPerUserSunday(datesToGetDatas[0]));
    	fentchDataTaskPerUserMonday =  new ArrayList<>(fentchDataTaskPerUserMonday(datesToGetDatas[1]));
    	fentchDataTaskPerUserTuesday =  new ArrayList<>(fentchDataTaskPerUserTuesday(datesToGetDatas[2]));
    	fentchDataTaskPerUserWednesday =  new ArrayList<>(fentchDataTaskPerUserWednesday(datesToGetDatas[3]));
    	fentchDataTaskPerUserThursday =  new ArrayList<>(fentchDataTaskPerUserThursday(datesToGetDatas[4]));
    	fentchDataTaskPerUserFriday =  new ArrayList<>(fentchDataTaskPerUserFriday(datesToGetDatas[5]));
    	fentchDataTaskPerUserSaturday =  new ArrayList<>(fentchDataTaskPerUserSaturday(datesToGetDatas[6]));
    	
    	try {
	    	for (int i = 0 ; i<fentchDataTaskPerUserSunday.size(); i++) 
	    		{
		    		FXMLLoader fxmlLoader = new FXMLLoader();
		    		fxmlLoader.setLocation(getClass().getResource("TaskCard.fxml"));
		    		VBox cardBox = fxmlLoader.load();
		    		TaskCardController cardcontroller = fxmlLoader.getController();
		    		cardcontroller.setData(fentchDataTaskPerUserSunday.get(i));
//		    		VboxSunday.getChildren().clear();
		    		VboxSunday.getChildren().add(cardBox);
				} 
    	}
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
    		System.out.println("list for monday 1  "+fentchDataTaskPerUserMonday.size());
	    	for (int i = 0 ; i<fentchDataTaskPerUserMonday.size(); i++) 
    		{
	    		
	    		FXMLLoader fxmlLoader = new FXMLLoader();
	    		fxmlLoader.setLocation(getClass().getResource("TaskCard.fxml"));
	    		VBox cardBox = fxmlLoader.load();
	    		TaskCardController cardcontroller = fxmlLoader.getController();
	    		cardcontroller.setData(fentchDataTaskPerUserMonday.get(i));
	    		//VboxMonday.getChildren().clear();
	    		VboxMonday.getChildren().add(cardBox);
			}
    	}
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
	    	for (int i = 0 ; i<fentchDataTaskPerUserTuesday.size(); i++) 
    		{
	    		FXMLLoader fxmlLoader = new FXMLLoader();
	    		fxmlLoader.setLocation(getClass().getResource("TaskCard.fxml"));
	    		VBox cardBox = fxmlLoader.load();
	    		TaskCardController cardcontroller = fxmlLoader.getController();
	    		cardcontroller.setData(fentchDataTaskPerUserTuesday.get(i));
	    		VboxTuesday.getChildren().add(cardBox);
			}
    	}
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
	    	for (int i = 0 ; i<fentchDataTaskPerUserWednesday.size(); i++) 
    		{
	    		FXMLLoader fxmlLoader = new FXMLLoader();
	    		fxmlLoader.setLocation(getClass().getResource("TaskCard.fxml"));
	    		VBox cardBox = fxmlLoader.load();
	    		TaskCardController cardcontroller = fxmlLoader.getController();
	    		cardcontroller.setData(fentchDataTaskPerUserWednesday.get(i));
	    		VboxWednesday.getChildren().add(cardBox);
			}
    	}
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
	    	for (int i = 0 ; i<fentchDataTaskPerUserThursday.size(); i++) 
    		{
	    		FXMLLoader fxmlLoader = new FXMLLoader();
	    		fxmlLoader.setLocation(getClass().getResource("TaskCard.fxml"));
	    		VBox cardBox = fxmlLoader.load();
	    		TaskCardController cardcontroller = fxmlLoader.getController();
	    		cardcontroller.setData(fentchDataTaskPerUserThursday.get(i));
	    		VboxThursday.getChildren().add(cardBox);
			}
    	}
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
	    	for (int i = 0 ; i<fentchDataTaskPerUserFriday.size(); i++) 
    		{
	    		FXMLLoader fxmlLoader = new FXMLLoader();
	    		fxmlLoader.setLocation(getClass().getResource("TaskCard.fxml"));
	    		VBox cardBox = fxmlLoader.load();
	    		TaskCardController cardcontroller = fxmlLoader.getController();
	    		cardcontroller.setData(fentchDataTaskPerUserFriday.get(i));
	    		VboxFriday.getChildren().add(cardBox);
			}
    	}
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
	    	for (int i = 0 ; i<fentchDataTaskPerUserSaturday.size(); i++) 
    		{
	    		FXMLLoader fxmlLoader = new FXMLLoader();
	    		fxmlLoader.setLocation(getClass().getResource("TaskCard.fxml"));
	    		VBox cardBox = fxmlLoader.load();
	    		TaskCardController cardcontroller = fxmlLoader.getController();
	    		cardcontroller.setData(fentchDataTaskPerUserSaturday.get(i));
	    		VboxSaturday.getChildren().add(cardBox);
			}
	    	
    	}
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//return;
    	
    }
    
    
   public static String[] GetFullWeekDate(int year, int month, int day) {
	   
	   String[] weekdates = new String[7];
	   
	   LocalDate currentDate = LocalDate.of(year, month, day);//YYYY-mm-dd
       DayOfWeek today = currentDate.getDayOfWeek();
       //LocalDate sunday = currentDate.minusDays(today.getValue() - DayOfWeek.SUNDAY.getValue());
       LocalDate sunday = currentDate.with(ChronoField.DAY_OF_WEEK, 1);

       System.out.println(currentDate.toString());
       System.out.println(today.toString());
       System.out.println(sunday.toString());
       
       
       
       for (int i = 0; i < DayOfWeek.values().length; i++) {
           LocalDate days = sunday.plusDays(i-1);
           System.out.println(days.getDayOfWeek() + ": " + days);
           weekdates[i] = days.toString();
           
       }
	   
//       System.out.println(weekdates[0]);
//       System.out.println(weekdates[1]);
//       System.out.println(weekdates[2]);
//       System.out.println(weekdates[3]);
//       System.out.println(weekdates[4]);
//       System.out.println(weekdates[5]);
//       System.out.println(weekdates[6]);
       
        
		return weekdates;
    	
    }
    void ShowTask() {
    	
				    	LocalDate currentDate = LocalDate.of(2023, 11, 29);//YYYY-mm-dd
				        DayOfWeek today = currentDate.getDayOfWeek();
				        //LocalDate sunday = currentDate.minusDays(today.getValue() - DayOfWeek.SUNDAY.getValue());
				        LocalDate sunday = currentDate.with(ChronoField.DAY_OF_WEEK, 1);
				
				        System.out.println(currentDate.toString());
				        System.out.println(today.toString());
				        System.out.println(sunday.toString());
				        
				        
				        
				        for (int i = 0; i < DayOfWeek.values().length; i++) {
				            LocalDate day = sunday.plusDays(i-1);
				            System.out.println(day.getDayOfWeek() + ": " + day);
				        }
				 	   
    	       }
    
    
    private List<Task> fentchDataTaskPerUserSunday(String dateFilter) {
    	
    	List<Task> lsSunday = new ArrayList<>();
    	
    	Connection conn = DatabaseManager.getConnection();

		String getUserInfo = "SELECT * FROM tasklist WHERE userID = ? AND Date = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(getUserInfo);
			stmt.setString(1, String.valueOf(usID));
			stmt.setString(2, dateFilter);
			
			//ResultSet result = stmt.executeQuery();
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) 
			{
				//System.out.println(result.getString("Title"));
				 Task task = new Task();
				 task.setTaskID(result.getString("taskID"));
				 task.setTitle(result.getString("Title"));
				 task.setDescription(result.getString("Description"));
				 task.setTaskDate(result.getString("Date"));
				 task.setTime(result.getString("Time"));
				 task.setStatus(result.getString("Status"));
				 lsSunday.add(task);
				 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NO DB CONNECTION");
		}
		
		return lsSunday;
    }
    
    private List<Task> fentchDataTaskPerUserMonday(String dateFilter) {
    	
    	
    	List<Task> lsMonday = new ArrayList<>();
    	lsMonday.clear();
    	Connection conn = DatabaseManager.getConnection();

		String getUserInfo = "SELECT * FROM tasklist WHERE userID = ? AND Date = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(getUserInfo);
			stmt.setString(1, String.valueOf(usID));
			stmt.setString(2, dateFilter);
			
			//ResultSet result = stmt.executeQuery();
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) 
			{
				//System.out.println(result.getString("Title"));
				 Task task = new Task();
				 task.setTaskID(result.getString("taskID"));
				 task.setTitle(result.getString("Title"));
				 task.setDescription(result.getString("Description"));
				 task.setTaskDate(result.getString("Date"));
				 task.setTime(result.getString("Time"));
				 task.setStatus(result.getString("Status"));
				 lsMonday.add(task);
				 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NO DB CONNECTION");
		}
		
		return lsMonday;
    }
    
    private List<Task> fentchDataTaskPerUserTuesday(String dateFilter) {
    	
    	List<Task> lsTuesday = new ArrayList<>();
    	
    	Connection conn = DatabaseManager.getConnection();

		String getUserInfo = "SELECT * FROM tasklist WHERE userID = ? AND Date = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(getUserInfo);
			stmt.setString(1, String.valueOf(usID));
			stmt.setString(2, dateFilter);
			
			//ResultSet result = stmt.executeQuery();
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) 
			{
				//System.out.println(result.getString("Title"));
				 Task task = new Task();
				 task.setTaskID(result.getString("taskID"));
				 task.setTitle(result.getString("Title"));
				 task.setDescription(result.getString("Description"));
				 task.setTaskDate(result.getString("Date"));
				 task.setTime(result.getString("Time"));
				 task.setStatus(result.getString("Status"));
				 lsTuesday.add(task);
				 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NO DB CONNECTION");
		}
		
		return lsTuesday;
    }
    
    private List<Task> fentchDataTaskPerUserWednesday(String dateFilter) {
    	
    	List<Task> lsWednesday = new ArrayList<>();
    	
    	Connection conn = DatabaseManager.getConnection();

		String getUserInfo = "SELECT * FROM tasklist WHERE userID = ? AND Date = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(getUserInfo);
			stmt.setString(1, String.valueOf(usID));
			stmt.setString(2, dateFilter);
			
			//ResultSet result = stmt.executeQuery();
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) 
			{
				//System.out.println(result.getString("Title"));
				 Task task = new Task();
				 task.setTaskID(result.getString("taskID"));
				 task.setTitle(result.getString("Title"));
				 task.setDescription(result.getString("Description"));
				 task.setTaskDate(result.getString("Date"));
				 task.setTime(result.getString("Time"));
				 task.setStatus(result.getString("Status"));
				 lsWednesday.add(task);
				 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NO DB CONNECTION");
		}
		
		return lsWednesday;
    }

    private List<Task> fentchDataTaskPerUserThursday(String dateFilter) {
	
	List<Task> lsThursday = new ArrayList<>();
	
	Connection conn = DatabaseManager.getConnection();

	String getUserInfo = "SELECT * FROM tasklist WHERE userID = ? AND Date = ?";
	try {
		PreparedStatement stmt = conn.prepareStatement(getUserInfo);
		stmt.setString(1, String.valueOf(usID));
		stmt.setString(2, dateFilter);
		
		//ResultSet result = stmt.executeQuery();
		
		ResultSet result = stmt.executeQuery();
		
		while (result.next()) 
		{
			//System.out.println(result.getString("Title"));
			 Task task = new Task();
			 task.setTaskID(result.getString("taskID"));
			 task.setTitle(result.getString("Title"));
			 task.setDescription(result.getString("Description"));
			 task.setTaskDate(result.getString("Date"));
			 task.setTime(result.getString("Time"));
			 task.setStatus(result.getString("Status"));
			 lsThursday.add(task);
			 
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("NO DB CONNECTION");
	}
	
	return lsThursday;
    }
    
    private List<Task> fentchDataTaskPerUserFriday(String dateFilter) {
    	
    	List<Task> lsFriday = new ArrayList<>();
    	
    	Connection conn = DatabaseManager.getConnection();

    	String getUserInfo = "SELECT * FROM tasklist WHERE userID = ? AND Date = ?";
    	try {
    		PreparedStatement stmt = conn.prepareStatement(getUserInfo);
    		stmt.setString(1, String.valueOf(usID));
    		stmt.setString(2, dateFilter);
    		
    		//ResultSet result = stmt.executeQuery();
    		
    		ResultSet result = stmt.executeQuery();
    		
    		while (result.next()) 
    		{
    			//System.out.println(result.getString("Title"));
    			 Task task = new Task();
    			 task.setTaskID(result.getString("taskID"));
    			 task.setTitle(result.getString("Title"));
    			 task.setDescription(result.getString("Description"));
    			 task.setTaskDate(result.getString("Date"));
    			 task.setTime(result.getString("Time"));
    			 task.setStatus(result.getString("Status"));
    			 lsFriday.add(task);
    			 
    		}
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    		System.out.println("NO DB CONNECTION");
    	}
    	
    	return lsFriday;
        }
    
    private List<Task> fentchDataTaskPerUserSaturday(String dateFilter) {
    	
    	List<Task> lsSaturday = new ArrayList<>();
    	
    	Connection conn = DatabaseManager.getConnection();

    	String getUserInfo = "SELECT * FROM tasklist WHERE userID = ? AND Date = ?";
    	try {
    		PreparedStatement stmt = conn.prepareStatement(getUserInfo);
    		stmt.setString(1, String.valueOf(usID));
    		stmt.setString(2, dateFilter);
    		
    		//ResultSet result = stmt.executeQuery();
    		
    		ResultSet result = stmt.executeQuery();
    		
    		while (result.next()) 
    		{
    			//System.out.println(result.getString("Title"));
    			 Task task = new Task();
    			 task.setTaskID(result.getString("taskID"));
    			 task.setTitle(result.getString("Title"));
    			 task.setDescription(result.getString("Description"));
    			 task.setTaskDate(result.getString("Date"));
    			 task.setTime(result.getString("Time"));
    			 task.setStatus(result.getString("Status"));
    			 lsSaturday.add(task);
    			 
    		}
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    		System.out.println("NO DB CONNECTION");
    	}
    	
    	return lsSaturday;
        }
        
    @FXML
   void CallFuncSearchWeek(ActionEvent event) {

    			
    			int year = 0, month = 0, day = 0;
    	    	LocalDate localDate = DTpickerBar.getValue();
    	    	String cdate = localDate.toString();
//    	    	 if(localDate == null) {
//    	    		 cdate = localDate.now().toString();
//    	    	 }
    	    	String[] arrLastDate = 	cdate.split("-");
    	    	
    	    	year = Integer.valueOf(arrLastDate[0]);
    	    	month = Integer.valueOf(arrLastDate[1]);
    	    	day = Integer.valueOf(arrLastDate[2]);
    	    	
    	    	System.out.println(cdate);
    	    	
    	    	//ShowPane.getChildren().clear();
    	    	VboxSunday.getChildren().clear();
    	    	VboxMonday.getChildren().clear();
    	    	VboxTuesday.getChildren().clear();
    	    	VboxWednesday.getChildren().clear();
    	    	VboxThursday.getChildren().clear();
    	    	VboxFriday.getChildren().clear();
    	    	VboxSaturday.getChildren().clear();
    	    	
    	    	GetTaskFromFullWeek(year,month,day);
    	    	
    	    	String[] dateGet = GetFullWeekDate(year, month, day);
    	    	
    	    	labeldateShow.setText(dateGet[0] +"     -     "+dateGet[6]);
    	    	
    }
    
}

