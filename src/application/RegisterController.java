
package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController implements Initializable {
	@FXML
	private TextField license_number_field;
	@FXML
	private TextField license_number_2_field;
	@FXML
	private TextField type_field;
	@FXML
	private TextField model_field;
	@FXML
	private TextField company_field;
	@FXML
	private ComboBox<String> owner_field;
	@FXML
	private ComboBox<String> driver_field;
	
	DBconnection con = new DBconnection();
	Connection connect = con.getConnection();
	ObservableList<String> owners = FXCollections.observableArrayList("new");
	ObservableList<String> drivers = FXCollections.observableArrayList("new");
	
	@FXML
	private void onRegisterCar(ActionEvent event) {
		DBconnection con = new DBconnection();
		Connection connect = con.getConnection();
		try {
			ResultSet rs = connect.createStatement().executeQuery("select * from person where name ='"+owner_field.getSelectionModel().getSelectedItem()+"' ");
			ResultSet qs = connect.createStatement().executeQuery("select * from person where name ='"+driver_field.getSelectionModel().getSelectedItem()+"' ");
			if(rs.next() && qs.next()) {
			int i = connect.createStatement().executeUpdate("Insert into car(license_number, type, company, model, date_added, owner, driver) values('"+license_number_field.getText()+"','"+type_field.getText()+"','"+company_field.getText()+"','"+model_field.getText()+"','"+LocalDateTime.now()+"','"+rs.getInt("id_card_no")+"','"+qs.getInt("id_card_no")+"')");
			if(i== 1) {
				CarInfoController carInfo = new CarInfoController();
				carInfo.getCarId(license_number_field.getText());
				carInfo.show(event);
				
		     	}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	private void onLogin(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("car_info.fxml"));
		loader.load();
		CarInfoController carInfo = (CarInfoController)loader.getController();
		carInfo.getCarId(license_number_2_field.getText());

		carInfo.show(event);
		
		
	}
	
	public void show(ActionEvent event) {
		Parent root;
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
            root = loader.load();
            
            loader.getController();
                       
            Stage stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
            stage.setScene(new Scene(root, 600, 484));
            stage.show();
            
            // Hide this current window (if this is what you want)
//            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}

	public ObservableList<String> getOwners() {
		ObservableList<String> owners = FXCollections.observableArrayList();
		try {
			ResultSet rs =  connect.createStatement().executeQuery("Select * from person where role = 'owner'");
			while(rs.next()) {
				owners.add(rs.getString("name"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return owners;
	}
	public ObservableList<String> getDrivers() {
		ObservableList<String> drivers = FXCollections.observableArrayList();
		try {
			ResultSet rs =  connect.createStatement().executeQuery("Select * from person where role = 'driver'");
			while(rs.next()) {
				drivers.add(rs.getString("name"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return drivers;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		owner_field.setOnAction(e ->{
			if(owner_field.getSelectionModel().getSelectedItem() == "new") {
				RegisterPersonController regPerson = new RegisterPersonController();
				
				regPerson.show(e);
			}
		});
		
		owners.addAll(getOwners());
		drivers.addAll(getDrivers());
		owner_field.setItems(owners);
		driver_field.setItems(drivers);
	
	}
}
