
package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterPersonController implements Initializable {
	@FXML
	private TextField id_card_no_field;
	@FXML
	private TextField name_field;
	@FXML
	private TextField drivers_llicense_no_field;
	@FXML
	private TextField place_of_purchase;
	@FXML
	private TextField tel_no_field;
	@FXML
	private TextField supplier_field;
	@FXML
	private ComboBox<String> sex_field;
	@FXML
	private DatePicker bought_date_field;
	@FXML
	private ComboBox<String> role_field;
	
	@FXML
	private void onRegisterPerson(ActionEvent event) {
		DBconnection con = new DBconnection();
		Connection connect = con.getConnection();
		
		try {
			
	if(role_field.getSelectionModel().getSelectedItem() == "Driver") {
	  int i = connect.createStatement().executeUpdate("insert into person(id_card_no, name, sex, address, d_license, tel, role) values('"+ id_card_no_field.getText()+"','"+name_field.getText()+"','"+sex_field.getSelectionModel().getSelectedItem().charAt(0)+"','somewhere','"+drivers_llicense_no_field.getText()+"','"+tel_no_field.getText()+"','"+role_field.getSelectionModel().getSelectedItem()+"') ");
	  	if(i ==1) {
	  		System.out.println("done");
	  		RegisterController r = new RegisterController();
			r.show(event);
	  	}
	}else {
		int i = connect.createStatement().executeUpdate("insert into person(id_card_no, name, sex, address, d_license, tel, role) values('"+ id_card_no_field.getText()+"','"+name_field.getText()+"','"+sex_field.getSelectionModel().getSelectedItem().charAt(0)+"','somewhere','"+drivers_llicense_no_field.getText()+"','"+tel_no_field.getText()+"','"+role_field.getSelectionModel().getSelectedItem()+"') ");
		int j = connect.createStatement().executeUpdate("insert into owner values('"+id_card_no_field.getText()+"', '"+bought_date_field.getValue()+"', '"+place_of_purchase.getText()+"', '"+supplier_field.getText()+"')");
		if(i == 1 && j ==1) {
			System.out.println("Success");
			RegisterController r = new RegisterController();
			r.show(event);
		}
	}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void show(ActionEvent event) {
		Parent root;
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("register_person.fxml"));
            root = loader.load();
            
            loader.getController();
                       
            Stage stage =  (Stage)((Node)(event.getSource())).getScene().getWindow();
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
		role_field.setOnAction(e->{
			if(role_field.getSelectionModel().getSelectedItem() == "Driver") {
				bought_date_field.setDisable(true);
				supplier_field.setDisable(true);
				 place_of_purchase.setDisable(true);
				
			}else {
				bought_date_field.setDisable(false);
				supplier_field.setDisable(false);
				 place_of_purchase.setDisable(false);

			}
		});
		ObservableList<String> role = FXCollections.observableArrayList("Driver", "Owner");
		ObservableList<String> sex = FXCollections.observableArrayList("Male", "Female");
		sex_field.setItems(sex);
		role_field.setItems(role);
	}
}
