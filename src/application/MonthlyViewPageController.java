package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
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
        fetchStatsNotSTarted();
        fetchStatsOngoing();
        fetchStatsFinished();
	}
	
    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
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
//        Map<Integer, List<CalendarActivity>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

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
                    int currentDate = calculatedDate - dateOffset;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

//                        List<CalendarActivity> calendarActivities = calendarActivityMap.get(currentDate);
//                        if(calendarActivities != null){
//                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
//                        }
                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }
    
    public void fetchStatsNotSTarted() {
    	Connection conn = DatabaseManager.getConnection();

		String getStatsNotStarted = "SELECT COUNT(*) AS count FROM tasklist WHERE userID = ? AND Status = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(getStatsNotStarted);
			stmt.setString(1, String.valueOf(usID));
			stmt.setString(2, "Not Started");
			
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
    public void fetchStatsOngoing() {
    	Connection conn = DatabaseManager.getConnection();

		String getStatsOngoing = "SELECT COUNT(*) AS count FROM tasklist WHERE userID = ? AND Status = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(getStatsOngoing);
			stmt.setString(1, String.valueOf(usID));
			stmt.setString(2, "On-going");
			
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
    public void fetchStatsFinished() {
    	Connection conn = DatabaseManager.getConnection();

		String getStatsOngoing = "SELECT COUNT(*) AS count FROM tasklist WHERE userID = ? AND Status = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(getStatsOngoing);
			stmt.setString(1, String.valueOf(usID));
			stmt.setString(2, "Finished");
			
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

}
