package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import socialmedia.TwitterSearch;

public class TwitterListController implements Initializable{
	@FXML private TextField fullNameTF;
	@FXML private Button searchBtn;
	@FXML private Button closeBtn;
	private WebEngine webEngine;
	@FXML private WebView webView;

	
	public void close(ActionEvent event){
		closeScene();
	}
	
	public void search(ActionEvent event){
		
		TwitterSearch twitterSearch = new TwitterSearch();
		String content = twitterSearch.serach("ali","reza");
		webEngine.loadContent(content);
		
	}
	
	private void closeScene(){
		Stage stage = (Stage) fullNameTF.getScene().getWindow();
	    stage.close();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		webEngine = webView.getEngine();
		
	}

}
