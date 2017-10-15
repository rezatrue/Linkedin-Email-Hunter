package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController  implements Initializable{
	@FXML private TextField usernameTF;
	@FXML private TextField passwordTF;
	@FXML private Button loginBtn;
	

	
	public void login(){
		String name = usernameTF.getText();
		String pass = passwordTF.getText();
		
		System.out.println("User name :" + name + " Password : " + pass);
		
		// varify credential than close 
		
		//https://stackoverflow.com/questions/25037724/how-to-close-a-java-window-with-a-button-click-javafx-project
		// closing scene 
		Stage stage = (Stage) usernameTF.getScene().getWindow();
	    stage.close();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
