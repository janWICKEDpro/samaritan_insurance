
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
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CarInfoController implements Initializable {
	@FXML
	private Label license_number_field;
	@FXML
	private Label type_field;
	@FXML
	private Label company_field;
	@FXML
	private Label date_field;
	@FXML
	private Label model_field;
	@FXML
	private Label owner_field;
	@FXML
	private Label driver_field;
	@FXML
	private Label insurance_paid_field;
	@FXML
	private Label accident_field;
	@FXML
	private Label insurance_left_field;
	
	@FXML
	private void onAccident(ActionEvent event) {
		
	}
	
	@FXML
	private void onPayInsurance(ActionEvent event) {
		
	}
	
	public void show() {
		Parent root;
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("car_info.fxml"));
            root = loader.load();
            
            loader.getController();
                       
            Stage stage = new Stage(); //(Stage)((Node)(event.getSource())).getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
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
