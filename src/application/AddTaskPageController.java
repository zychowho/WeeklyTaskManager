package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddTaskPageController extends MainFrameController  {
	@FXML
	private TextField inputTitle;
	@FXML
	private TextField inputDescription;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
    @FXML
    private DatePicker selectDateButton;
    @FXML
    private Label lblerrorMessage;
    @FXML
    private ComboBox<String> btnComboBoxStatus,btnIndicator,btnHr,btnMin;
    
    
    
    public void initialize() {
        ObservableList<String> Statuslist = FXCollections.observableArrayList("Not Started","On-going","Finished");
        ObservableList<String> hour = FXCollections.observableArrayList("0","1","2","3","4","5","6","7","8","9","10","11","12");
        ObservableList<String> min = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20", 
        		"21","22","23","24","25","26","27","28","29","30","31","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45",
        		"46","47","48","49","50","51","52","53","54","55","56","57","58","59","60"	);
        ObservableList<String> indicators = FXCollections.observableArrayList("AM","PM");
        
    	btnComboBoxStatus.setItems(Statuslist);
    	btnHr.setItems(hour);
    	btnMin.setItems(min);
    	btnIndicator.setItems(indicators);
    	
    	
    	// This is to disable previous day!
    	selectDateButton.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) < 0 );
            }
        });
	}
    
    @FXML
    void callCancelFunc(ActionEvent event) {
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void callAddTaskFunc(ActionEvent event) {
    	createTask();

    }
    
    public void createTask() {

   	 	
    	if(!inputTitle.getText().isBlank() && !inputDescription.getText().isBlank()) 
    	{
        	String stats = btnComboBoxStatus.getSelectionModel().getSelectedItem().toString();
        	String hr = btnHr.getSelectionModel().getSelectedItem().toString();
        	String min = btnMin.getSelectionModel().getSelectedItem().toString();
        	String indicator = btnIndicator.getSelectionModel().getSelectedItem().toString();
        	String time = (hr+ ":"+ min +" "+ indicator).toString();
        	System.out.println(time);
       	 	String date = selectDateButton.getValue().toString();
    		Connection con = DatabaseManager.getConnection();
        		
        	String insertNewUser = "INSERT INTO tasklist(userID,Title,Description,Date,Time,Status) VALUES (?,?,?,?,?,?)";
	        try {
	        	PreparedStatement stmt = con.prepareStatement(insertNewUser);
	     
	    		stmt.setString(1, String.valueOf(MainFrameController.usID));
	    		stmt.setString(2, inputTitle.getText());
	    		stmt.setString(3, inputDescription.getText());	
	    		stmt.setString(4, date);
	    		stmt.setString(5, time);
	    		stmt.setString(6, stats);
	        			
	    		stmt.executeUpdate();
	    				
	    		Stage stage = (Stage) btnSave.getScene().getWindow();
	    		stage.close();
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    		lblerrorMessage.setText("Error in Database");
	    	}	 
    		    		
    	} else {
			lblerrorMessage.setText("Please complete fields!");	
    	}
    	
    }
   
}