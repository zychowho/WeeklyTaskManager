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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainFrameController extends LoginPageController {

    @FXML
    private Button btnLogout, btnClose;

    @FXML
    private Button btnUpdateUser;

    @FXML
    private Label labelUser;
    
    public void fetchDataUser() {
    	
    }

    @FXML
    void callLogoutFunc(ActionEvent event) {
    	Stage stage = (Stage) btnLogout.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void callUpdateUserFunc(ActionEvent event) {
//    	System.out.println(this.usID);
    }

    @FXML
    void closeApp(ActionEvent event) {
    	Stage stage = (Stage) btnClose.getScene().getWindow();
    	stage.close();
    }

}
