package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MonthlyViewPageController extends MainFrameController implements Initializable {

    ZonedDateTime dateFocus;
    ZonedDateTime today;
	
    @FXML
    private Button btnClose;

    @FXML
    private Button btnMinimize;

    @FXML
    private Text month;

    @FXML
    private Text year;

    @FXML
    private FlowPane calendar;
    
    @FXML
    private Label labelNotStarted ,labelOngoing,labelFinished;

    @FXML
    void closeAppFunc(ActionEvent event) {

    }

    @FXML
    void minimizeFunc(ActionEvent event) {

    }

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
        fetchStatsNotSTarted(dateFocus.getMonthValue(),dateFocus.getYear());
        fetchStatsOngoing(dateFocus.getMonthValue(),dateFocus.getYear());
        fetchStatsFinished(dateFocus.getMonthValue(),dateFocus.getYear());
        System.out.println("----------------");
	}
	
    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        fetchStatsNotSTarted(dateFocus.getMonthValue(),dateFocus.getYear());
        fetchStatsOngoing(dateFocus.getMonthValue(),dateFocus.getYear());
        fetchStatsFinished(dateFocus.getMonthValue(),dateFocus.getYear());
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        fetchStatsNotSTarted(dateFocus.getMonthValue(),dateFocus.getYear());
        fetchStatsOngoing(dateFocus.getMonthValue(),dateFocus.getYear());
        fetchStatsFinished(dateFocus.getMonthValue(),dateFocus.getYear());
        calendar.getChildren().clear();
        drawCalendar();
    }
	
    private void drawCalendar(){
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        //List of activities for a given month
        Map<Integer, List<CalendarActivity>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        //Check for leap year
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth =(calendarWidth/7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight/6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j+1)+(7*i);
                if(calculatedDate > dateOffset){
//                    System.out.println(calculatedDate);
                    int currentDate = calculatedDate - dateOffset;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        List<CalendarActivity> calendarActivities = calendarActivityMap.get(currentDate);
                        if(calendarActivities != null){
                        	createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }
    
    public void fetchStatsNotSTarted(Integer fetchDate,Integer fetchYear) {
    	Connection conn = DatabaseManager.getConnection();

		String getStatsNotStarted = "SELECT COUNT(*) AS count FROM tasklist WHERE userID = ? AND Status = ? AND MONTH(Date) = ? AND YEAR(Date) = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(getStatsNotStarted);
			stmt.setString(1, String.valueOf(usID));
			stmt.setString(2, "Not Started");
			stmt.setString(3, String.valueOf(fetchDate));
			stmt.setString(4, String.valueOf(fetchYear));
			
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
		        int count = result.getInt("count");
		        labelNotStarted.setText( String.valueOf(count));
		    }
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NO DB CONNECTION");
		}
		
    }
    public void fetchStatsOngoing(Integer fetchDate,Integer fetchYear) {
    	Connection conn = DatabaseManager.getConnection();

		String getStatsOngoing = "SELECT COUNT(*) AS count FROM tasklist WHERE userID = ? AND Status = ? AND MONTH(Date) = ? AND YEAR(Date) = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(getStatsOngoing);
			stmt.setString(1, String.valueOf(usID));
			stmt.setString(2, "On-going");
			stmt.setString(3, String.valueOf(fetchDate));
			stmt.setString(4, String.valueOf(fetchYear));
			
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
		        int count = result.getInt("count");
		        labelOngoing.setText( String.valueOf(count));
		    }
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NO DB CONNECTION");
		}
		
    }
    public void fetchStatsFinished(Integer fetchDate,Integer fetchYear) {
    	Connection conn = DatabaseManager.getConnection();

		String getStatsOngoing = "SELECT COUNT(*) AS count FROM tasklist WHERE userID = ? AND Status = ? AND MONTH(Date) = ?  AND YEAR(Date) = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(getStatsOngoing);
			stmt.setString(1, String.valueOf(usID));
			stmt.setString(2, "Finished");
			stmt.setString(3, String.valueOf(fetchDate));
			stmt.setString(4, String.valueOf(fetchYear));
			
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
		        int count = result.getInt("count");
		        labelFinished.setText( String.valueOf(count));
		    }
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NO DB CONNECTION");
		}
		
    }

    private void createCalendarActivity(List<CalendarActivity> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();
        for (int k = 0; k < calendarActivities.size(); k++) {
            if(k >= 2) {
                Text moreActivities = new Text("...");
                calendarActivityBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                    //On ... click print all activities for given date
                    System.out.println(calendarActivities);
                });
                break;
            }
            Text text = new Text(calendarActivities.get(k).getTaskTime() + ", " + calendarActivities.get(k).getTaskTitle());
            calendarActivityBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                //On Text clicked
                System.out.println(text.getText());
            });
        }
        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        calendarActivityBox.setStyle("-fx-background-color:GRAY");
//        calendarActivityBox.setPadding(20)
        stackPane.getChildren().add(calendarActivityBox);
    }

    private Map<Integer, List<CalendarActivity>> createCalendarMap(List<CalendarActivity> calendarActivities) {
        Map<Integer, List<CalendarActivity>> calendarActivityMap = new HashMap<>();

//        System.out.println(calendarActivities);
        for (CalendarActivity activity: calendarActivities) {
            int activityDate = activity.getDate().getDayOfMonth();

            if(!calendarActivityMap.containsKey(activityDate)){
                calendarActivityMap.put(activityDate, List.of(activity));
                System.out.println(activityDate);
            } else {
                List<CalendarActivity> OldListByDate = calendarActivityMap.get(activityDate);

                List<CalendarActivity> newList = new ArrayList<>(OldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return  calendarActivityMap;
    }

    private Map<Integer, List<CalendarActivity>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        
    	List<CalendarActivity> calendarActivities = new ArrayList<>();
    	Connection conn = DatabaseManager.getConnection();

		String getTasks = "SELECT * FROM tasklist WHERE userID = ? AND MONTH(Date) = ? AND YEAR(Date) = ?";
		try {	
			PreparedStatement stmt = conn.prepareStatement(getTasks);
			stmt.setString(1, String.valueOf(usID));
			stmt.setString(2, String.valueOf(dateFocus.getMonthValue()));
			stmt.setString(3, String.valueOf(dateFocus.getYear()));
			
			ResultSet result = stmt.executeQuery();
			while (result.next()) {

				Date dates = result.getDate("Date");
			
				LocalDate  localDate = dates.toLocalDate();
				ZonedDateTime columnDate = localDate.atStartOfDay(ZoneId.systemDefault());
                String ColumnTitle = result.getString("Title");
                String ColumnTime = result.getString("Time");
                calendarActivities.add(new CalendarActivity(columnDate,ColumnTitle,ColumnTime));
            }
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NO DB CONNECTION");
		}
        
//		System.out.println(calendarActivities);
        return createCalendarMap(calendarActivities);
    }
}
