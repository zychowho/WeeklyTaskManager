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
    void closeAppFunc(ActionEvent event) {

    }

    @FXML
    void minimizeFunc(ActionEvent event) {

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
    
    public static String GetFullWeekDatebtn() {
    	String str = null;
    	
  
    	
    	//GetTaskFromFullWeek(year,month,day);
//    	btnTest.setOnMouseClicked(event ->{
//				
//				
//			
//		
//			});
    	return str;
    }
    
   public static String[] GetFullWeekDate(int year, int month, int day) {
	   
//	   int year = 2024;
//   	int month = 1;
//   	int day = 1;
   	
   	LocalDate localDate = LocalDate.of(year, month, day);
	    java.time.DayOfWeek dayOfWeeks = localDate.getDayOfWeek();
	   //String dayOfWeeks = new SimpleDateFormat("EEEE").format(localDate);
	    
	   LocalDate localDateweek = LocalDate.of(year, month, day); // assuming we picked 18 September 2014
      int weekNumber = localDateweek.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
      //int datestring = localDateweek.get(IsoFields.);
      
      int enteredWeekNumber = weekNumber;
      int enteredYear = year;
     
      String DLastDateOfMonth = null;
      String DSunday = null,DMonday = null,DTuesday= null,DWednesday= null,DThursday= null,DFriday= null,DSaturday= null;
      
      LocalDate Sunday = LocalDate.now()
              .withYear(enteredYear)
              .with(ChronoField.ALIGNED_WEEK_OF_YEAR, enteredWeekNumber)
			   .with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
  			   DSunday = Sunday.toString();
      
  	   String[] arrSunday = 	DSunday.split("-");
  	   
  	   int sunYear = Integer.valueOf(arrSunday[0].toString());
	   int sunMonth = Integer.valueOf(arrSunday[1].toString());
	   int sunDay = Integer.valueOf(arrSunday[2].toString());
	   
	   LocalDate localDateweeklast = LocalDate.of(sunYear, sunMonth, sunDay); // assuming we picked 18 September 2014
      int weekNumberlast = localDateweeklast.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
  			  
  	   LocalDate LastDateOfMonth = LocalDate.now()
  	            .withYear(sunYear)
  	            .with(ChronoField.ALIGNED_WEEK_OF_YEAR, weekNumberlast)
  				.with(TemporalAdjusters.lastDayOfMonth());
  	   			DLastDateOfMonth = LastDateOfMonth.toString();
  	   			
  	   			
  	   				
  	   		String[] arrLastDate = 	DLastDateOfMonth.split("-");
  	   		
  	   
  	   
  	   int LastDYear = Integer.valueOf(arrLastDate[0].toString());
	   int LastDMonth = Integer.valueOf(arrLastDate[1].toString());
	   int LastDDay = Integer.valueOf(arrLastDate[2].toString());
	   
	   int mon = 0,tue = 0,wed = 0,thur = 0,fri = 0,sat =0;
	   int Mmon = 0,Mtue = 0,Mwed = 0,Mthur = 0,Mfri = 0,Msat =0;
	   int Ymon = 0,Ytue = 0,Ywed = 0,Ythur = 0,Yfri = 0,Ysat =0;
	   
	   
	   //monday
	   if(sunDay < LastDDay) 
	   {
		   mon = (sunDay+1);
		   Mmon = LastDMonth;
		   Ymon = sunYear;
	   }
	   else if(sunDay == LastDDay) 
	   {
		   mon = 1;
		   if(LastDMonth < 12) 
		   {
			   Mmon = LastDMonth+1;
			   Ymon = sunYear;
		   }
		   else {Mmon =1; Ymon = sunYear+1;}
		}
	   //tuesday
	   if(mon < LastDDay) {tue = (mon+1);  Mtue = Mmon; Ytue = Ymon;}
	   else if(mon == LastDDay) 
	   {
		   tue = 1;
		   if(LastDMonth < 12) 
		   {
			   Mtue = LastDMonth+1;
			   Ytue = Ymon;
		   }
		   else {Mtue =1; Ytue = Ymon+1;}
		   }
	   //wednesday
	   if(tue < LastDDay) {wed =(tue +1); Mwed = Mtue; Ywed = Ytue;}
	   else if(tue == LastDDay)
	   {
		   wed = 1;
		   if(LastDMonth < 12) 
		   {
			   Mwed = Mtue+1;
			   Ywed = Ytue;
		   }
		   else {Mwed =1; Ywed = Ytue+1;}
		   
		}
	   //thursday
	   if(wed < LastDDay) {thur =(wed +1); Mthur = Mwed; Ythur =Ywed;} 
	   else if(wed == LastDDay) 
	   {
		   thur = 1;
		   if(LastDMonth < 12) 
		   {
			   Mthur = Mwed+1;
			   Ythur =Ywed;
		   }
		   else {Mthur =1; Ythur =Ywed+1;}
		   }
	   //friday
	   if(thur < LastDDay) {fri =(thur +1);  Mfri = Mthur; Yfri =Ythur;}
	   else if(thur == LastDDay) 
	   {
		   fri = 1;
		   if(LastDMonth < 12) 
		   {
			   Mfri = Mthur+1;
			   Yfri =Ythur;
		   }
		   else {Mfri =1; Yfri =Ythur+1;}
		   
	   }
	   //saturday
	   if(fri < LastDDay) {sat =(fri +1); Msat = Mfri; Ysat = Yfri;}
	   else if(fri == LastDDay) 
	   {
		   sat = 1;
		   if(LastDMonth < 12) 
		   {
			   Msat = Mfri+1;
			   Ysat = Yfri;
		   }
		   else {Msat =1; Ysat = Yfri+1;}
		}
  
	   
	   
//	   System.out.println(dayOfWeeks + "  :  "+ DSunday + "  :  "+DLastDateOfMonth);
//	   System.out.println("Sunday" + DSunday);
//	   System.out.println("Monday" +" : "+ mon +" : "+ Mmon +" : "+Ymon);
//	   System.out.println("Tuesday" +" : "+ tue +" : "+ Mtue +" : "+Ytue);
//	   System.out.println("Wednesday" +" : "+ wed +" : "+ Mwed+" : "+Ywed);
//	   System.out.println("Thursday" +" : "+ thur +" : "+ Mthur+" : "+Ythur);
//	   System.out.println("Friday" +" : "+ fri +" : "+ Mfri+" : "+Yfri);
//	   System.out.println("Saturday" +" : "+ sat +" : "+ Msat+" : "+Ysat);
	   
	   DMonday = Ymon+"-"+Mmon+"-"+mon;
	   DTuesday = Ytue+"-"+Mtue+"-"+tue;
	   DWednesday = Ywed+"-"+Mwed+"-"+wed;
	   DThursday = Ythur+"-"+Mthur+"-"+thur;
	   DFriday = Yfri+"-"+Mfri+"-"+fri;
	   DSaturday = Ysat+"-"+Msat+"-"+sat;
        System.out.println(DSunday +" : "+DMonday+" : "+DTuesday+" : "+DWednesday+" : "+DThursday+" : "+DFriday+" : "+DSaturday);
        
        String[] weekdates = {DSunday,DMonday,DTuesday,DWednesday,DThursday,DFriday,DSaturday};
        
		return weekdates;
    	
    }
    void ShowTask() {
    	
    	int year = 2024;
    	int month = 1;
    	int day = 1;
    	
    	LocalDate localDate = LocalDate.of(year, month, day);
 	    java.time.DayOfWeek dayOfWeeks = localDate.getDayOfWeek();
 	   //String dayOfWeeks = new SimpleDateFormat("EEEE").format(localDate);
 	    
 	   LocalDate localDateweek = LocalDate.of(year, month, day); // assuming we picked 18 September 2014
       int weekNumber = localDateweek.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
       //int datestring = localDateweek.get(IsoFields.);
       
       int enteredWeekNumber = weekNumber;
       int enteredYear = year;
      
       String DLastDateOfMonth = null;
       String DSunday = null,DMonday = null,DTuesday= null,DWednesday= null,DThursday= null,DFriday= null,DSaturday= null;
       
       LocalDate Sunday = LocalDate.now()
               .withYear(enteredYear)
               .with(ChronoField.ALIGNED_WEEK_OF_YEAR, enteredWeekNumber)
			   .with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
   			   DSunday = Sunday.toString();
       
   	   String[] arrSunday = 	DSunday.split("-");
   	   
   	   int sunYear = Integer.valueOf(arrSunday[0].toString());
	   int sunMonth = Integer.valueOf(arrSunday[1].toString());
	   int sunDay = Integer.valueOf(arrSunday[2].toString());
	   
	   LocalDate localDateweeklast = LocalDate.of(sunYear, sunMonth, sunDay); // assuming we picked 18 September 2014
       int weekNumberlast = localDateweeklast.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
   			  
   	   LocalDate LastDateOfMonth = LocalDate.now()
   	            .withYear(sunYear)
   	            .with(ChronoField.ALIGNED_WEEK_OF_YEAR, weekNumberlast)
   				.with(TemporalAdjusters.lastDayOfMonth());
   	   			DLastDateOfMonth = LastDateOfMonth.toString();
   	   			
   	   			
   	   				
   	   		String[] arrLastDate = 	DLastDateOfMonth.split("-");
   	   		
   	   
   	   
   	   int LastDYear = Integer.valueOf(arrLastDate[0].toString());
	   int LastDMonth = Integer.valueOf(arrLastDate[1].toString());
	   int LastDDay = Integer.valueOf(arrLastDate[2].toString());
	   
	   int mon = 0,tue = 0,wed = 0,thur = 0,fri = 0,sat =0;
	   int Mmon = 0,Mtue = 0,Mwed = 0,Mthur = 0,Mfri = 0,Msat =0;
	   int Ymon = 0,Ytue = 0,Ywed = 0,Ythur = 0,Yfri = 0,Ysat =0;
	   
	   
	   //monday
	   if(sunDay < LastDDay) 
	   {
		   mon = (sunDay+1);
		   Mmon = LastDMonth;
		   Ymon = sunYear;
	   }
	   else if(sunDay == LastDDay) 
	   {
		   mon = 1;
		   if(LastDMonth < 12) 
		   {
			   Mmon = LastDMonth+1;
			   Ymon = sunYear;
		   }
		   else {Mmon =1; Ymon = sunYear+1;}
		}
	   //tuesday
	   if(mon < LastDDay) {tue = (mon+1);  Mtue = Mmon; Ytue = Ymon;}
	   else if(mon == LastDDay) 
	   {
		   tue = 1;
		   if(LastDMonth < 12) 
		   {
			   Mtue = LastDMonth+1;
			   Ytue = Ymon;
		   }
		   else {Mtue =1; Ytue = Ymon+1;}
		   }
	   //wednesday
	   if(tue < LastDDay) {wed =(tue +1); Mwed = Mtue; Ywed = Ytue;}
	   else if(tue == LastDDay)
	   {
		   wed = 1;
		   if(LastDMonth < 12) 
		   {
			   Mwed = Mtue+1;
			   Ywed = Ytue;
		   }
		   else {Mwed =1; Ywed = Ytue+1;}
		   
		}
	   //thursday
	   if(wed < LastDDay) {thur =(wed +1); Mthur = Mwed; Ythur =Ywed;} 
	   else if(wed == LastDDay) 
	   {
		   thur = 1;
		   if(LastDMonth < 12) 
		   {
			   Mthur = Mwed+1;
			   Ythur =Ywed;
		   }
		   else {Mthur =1; Ythur =Ywed+1;}
		   }
	   //friday
	   if(thur < LastDDay) {fri =(thur +1);  Mfri = Mthur; Yfri =Ythur;}
	   else if(thur == LastDDay) 
	   {
		   fri = 1;
		   if(LastDMonth < 12) 
		   {
			   Mfri = Mthur+1;
			   Yfri =Ythur;
		   }
		   else {Mfri =1; Yfri =Ythur+1;}
		   
	   }
	   //saturday
	   if(fri < LastDDay) {sat =(fri +1); Msat = Mfri; Ysat = Yfri;}
	   else if(fri == LastDDay) 
	   {
		   sat = 1;
		   if(LastDMonth < 12) 
		   {
			   Msat = Mfri+1;
			   Ysat = Yfri;
		   }
		   else {Msat =1; Ysat = Yfri+1;}
		}
   
	   
	   
// 	   System.out.println(dayOfWeeks + "  :  "+ DSunday + "  :  "+DLastDateOfMonth);
// 	   System.out.println("Sunday" + DSunday);
// 	   System.out.println("Monday" +" : "+ mon +" : "+ Mmon +" : "+Ymon);
// 	   System.out.println("Tuesday" +" : "+ tue +" : "+ Mtue +" : "+Ytue);
//	   System.out.println("Wednesday" +" : "+ wed +" : "+ Mwed+" : "+Ywed);
//	   System.out.println("Thursday" +" : "+ thur +" : "+ Mthur+" : "+Ythur);
// 	   System.out.println("Friday" +" : "+ fri +" : "+ Mfri+" : "+Yfri);
// 	   System.out.println("Saturday" +" : "+ sat +" : "+ Msat+" : "+Ysat);
 	   
 	   DMonday = Ymon+"-"+Mmon+"-"+mon;
 	   DTuesday = Ytue+"-"+Mtue+"-"+tue;
 	   DWednesday = Ywed+"-"+Mwed+"-"+wed;
	   DThursday = Ythur+"-"+Mthur+"-"+thur;
	   DFriday = Yfri+"-"+Mfri+"-"+fri;
 	   DSaturday = Ysat+"-"+Msat+"-"+sat;
 	   
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
    	//DatePicker dt = new DatePicker();
    	//dt = DTpickertest.getValue();
    	
    	//LocalDate localDate = DTpickerBar.getValue();
    	//Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
    	//Date date = Date.from(instant);
    	//System.out.println(localDate + " - "+instant+" - "+date);
    		//String cdate = null;
    			
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

