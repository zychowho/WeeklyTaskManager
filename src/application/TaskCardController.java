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

import javafx.scene.control.TextField;
//import com.gluonhq.charm.glisten.control.DropdownButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Task;

public class TaskCardController {

    
    //private DropdownButton DpbtnStatus;
	@FXML
    private VBox Box;

    @FXML
    private Label labelDate;

    @FXML
    private Text labelDescription;
    
    private String msg;

    @FXML
    private Label labelTitle;

//    private String [] colors = { "ece0d1","dbc1ac","C8B6A6"};
    
    public void setData(Task task) {
//    	System.out.println(task.getTitle());
//    	System.out.println(task.getDescription());
//    	System.out.println(task.getTaskDate());
    	
    	//msg = task.getDescription();
    	
    	labelTitle.setText(task.getTitle());
    	
    	//labelDescription.setText(msg);
    	labelDescription.setText(task.getDescription());
    	//labelDescription.setText(task.getDescription());
    	//task.getStatus();
    	//labelDescription.getText(task.getDescription());
    	//labelDescription.setText("Leo");
    	labelDate.setText(task.getTaskDate());
    	Box.setStyle("-fx-background-color : #F4EAE0;"+ 
    			"-fx-background-radius : 15;" + "-fx-margin : 20 0 0 20;");
    	
    }
    
}
