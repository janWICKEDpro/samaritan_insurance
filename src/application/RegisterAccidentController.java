
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterAccidentController implements Initializable {
	@FXML
	private TextField place_field;
	@FXML
	private ComboBox<String> degree_field;
	@FXML
	private DatePicker time_field;
	
	@FXML
	private void onRegisterAccident(ActionEvent event) {
		
	}
	
	public void show() {
		Parent root;
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("register_accident.fxml"));
            root = loader.load();
            
            loader.getController();
                       
            Stage stage = new Stage(); //(Stage)((Node)(event.getSource())).getScene().getWindow();
            stage.setScene(new Scene(root, 600, 266));
            stage.show();
            
            // Hide this current window (if this is what you want)
//            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	
	}
}
