
package application;

import java.io.IOException;
import javafx.scene.Node;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
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
	
	DBconnection c = new DBconnection();
	Connection con = c.getConnection();
	
	@FXML
	private void onAccident(ActionEvent event) {
		
	}
	
	@FXML
	private void onPayInsurance(ActionEvent event) {
		
	}
	
	public void show(ActionEvent event) {
		Parent root;
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("car_info.fxml"));
            root = loader.load();
            
           // loader.getController();
                       
            Stage stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
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
		
		try {
			ResultSet r = con.createStatement().executeQuery("select * from car where license_number = '"+license_number_field.getText()+"' ");
			ResultSet s = con.createStatement().executeQuery("select * from insurance where license_number = '"+license_number_field.getText()+"'");
			ResultSet q = con.createStatement().executeQuery("select * from accident where license_number = '"+license_number_field.getText()+"'");
			while(r.next()) {
				type_field.setText("type");
				company_field.setText(r.getString("company"));
				date_field.setText(r.getDate("date_added").toString());
				owner_field.setText(" '"+r.getInt("owner")+"' ");
				driver_field.setText(" '"+r.getInt("driver")+"' ");
				accident_field.setText(" '"+q.getInt("acc_id")+"' ");
				insurance_paid_field.setText("'"+s.getDouble("amount")+"'");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public void getCarId(String license) throws IOException {
		license_number_field.setText(license);
	
	}
}
