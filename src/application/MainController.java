package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;

import data.Person;
import dataprocessor.EmailPermutator;
import dataprocessor.ExtractProfileInfo;
import dataprocessor.MailBoxValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import webhandler.ChromeOperator;
import webhandler.EmployeList;

public class MainController implements Initializable{
	@FXML private TextField firstNameTF;
	@FXML private TextField lastNameTF;
	@FXML private TextField webUrlTF;
	@FXML private TextArea listTA;
	@FXML private Button generateBtn;
	@FXML private Button varifyBtn;
	
	@FXML private TextField prifileUrlTF;
	@FXML private Button openBrowserBtn;
	@FXML private Button startBtn;
	
	@FXML private TextField emailTF;
	@FXML private Button takeInBtn;
	@FXML private Button takeOutBtn;
	
	@FXML private ImageView profileIV;
	
	@FXML private WebView profileWV;
	@FXML private WebView logoWV;
	private WebEngine engineProfile, engineLogo;
	private String sourceCode;
	private Person person;
	private ChromeOperator  chromeOperator;
	private ExtractProfileInfo extractProfileInfo;

	
	@FXML private Label designationL;
	@FXML private TextField compamyNameTF;
	@FXML private TextField linkedinCompamyPageTF;
	@FXML private Button companyInfotBtn;
	
	@FXML private TextField currentPageTF;
	@FXML private TextField targetPageTF;
	@FXML private Button prevPageBtn;
	@FXML private Button nextPageBtn;

	
	// need to add pop alert 
	private void popupErrorMassage(String msg){
		System.out.println("------- >>>> "+ msg +"  <<<< -------");
	}
	
	public void open(){
		if(chromeOperator==null){
			chromeOperator = new ChromeOperator();
			if(chromeOperator.openChromewithSelenium()){
				chromeOperator.openpage("https://www.linkedin.com");
				if(!chromeOperator.loginLinkedinAccount())
					popupErrorMassage("unable to login");
				openBrowserBtn.setText("Close");
			}else 
				popupErrorMassage("unable to open browser");
			
		}else{
			// need to add an Alert before close browser
			chromeOperator.openpage("https://www.linkedin.com/m/logout/");
			chromeOperator.close();
			chromeOperator = null;
			openBrowserBtn.setText("Open");
		}
	}
	
	private void openUrl(){
		String url = prifileUrlTF.getText().toString().trim();
		if(!(url.toLowerCase().startsWith("https://www.linkedin.com/"))) 
			popupErrorMassage("Invalid URL");
		else{
			sourceCode = chromeOperator.openProfile(url);
		}
	}
	
	
	private void setProfile() {
		// TODO Auto-generated method stub
		extractProfileInfo = new ExtractProfileInfo();
		
		person = extractProfileInfo.extractInfo(this.sourceCode);
		firstNameTF.setText(person.getFirstname());
		lastNameTF.setText(person.getLastname());
		webUrlTF.setText(person.getWebsite());
		listTA.setText(person.getEmail());
		engineProfile.load(person.getImage());
		engineLogo.load(person.getCompanylogo());
		designationL.setText(person.getDesignation());
		compamyNameTF.setText(person.getCompany());
		linkedinCompamyPageTF.setText(person.getCompanylinkedinpage());
		person.toString();
	}
	private void clearScreen(){
		sourceCode = "";
		firstNameTF.setText("");
		lastNameTF.setText("");
		webUrlTF.setText("");
		listTA.setText("");
		engineProfile.load("");
		engineLogo.load("");
	}
	
	public void start(){
		clearScreen();
		openUrl();
		setProfile();
		// https://www.linkedin.com/in/yaronzoller/
		// https://www.linkedin.com/in/nabila-tajrin-187aab84/
	}
	
	
	public void gotonextPage(){
		int num = chromeOperator.nextEmplyeeListPage();
		if(num== -1)
			popupErrorMassage("Error opening next page");
		else 
			pageProcessing(num);
	}
	
	private void pageProcessing(int num){
		currentPageTF.setText(String.valueOf(num));
		String url = chromeOperator.getPageUrl();
		String source = chromeOperator.takeListSource();
		emplyeeData(source);
		chromeOperator.openpage(url);
	}
	
	public void gotoprevPage(){
		int num = chromeOperator.prevEmplyeeListPage();
		if(num== -1)
			popupErrorMassage("Error opening prev page");
		else
			pageProcessing(num);
	}
	
	
	
	public void companyInfo(){
		if(linkedinCompamyPageTF.getText().isEmpty()){
			String compurl = person.getCompanylinkedinpage();
			linkedinCompamyPageTF.setText(compurl);
		}
		if(!linkedinCompamyPageTF.getText().contains("https://www.linkedin.com/company")){
			popupErrorMassage("Invalid company URL");
		}else{
			chromeOperator.openpage(linkedinCompamyPageTF.getText());
			String source = chromeOperator.clicktakeListSource();
			String pageUrl = chromeOperator.getPageUrl();
			emplyeeData(source);
			chromeOperator.openpage(pageUrl);
		}
	}
	
	private void emplyeeData(String source){
		EmployeList employeList = new EmployeList(compamyNameTF.getText(), source);
		ArrayList<String> list = employeList.takeList();
		
		System.out.println(list.size());
		
		Iterator it = list.iterator();
		extractProfileInfo = new ExtractProfileInfo();
		Person person = new Person();
		while(it.hasNext()){
			String sourceCode = chromeOperator.openProfile(it.next().toString());
			person = extractProfileInfo.extractInfo(sourceCode);
			System.out.println(person.getFirstname() + " " + person.getLastname()
					+ person.getEmail());
		}
	}

	public void listTakeIn(){
		String newlist = listTA.getText().replaceAll("\n", " ");
		emailTF.appendText(" "+ newlist.trim());
		listTA.setText("");
	}
	
	public void listTakeOut(){
		String newlist = emailTF.getText().replaceAll(" ", "\n");
		listTA.appendText(newlist.trim() + "\n");
		emailTF.setText("");		
	}	
	
	// return 0 for stop & -1 for continue 
	private int popupAlert(String email){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("We get a vaild email Adress : \n " +"\" " + email +" \"" );
		alert.setContentText("Do u what to skip the rest of the list?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    return 0;
		} else {
			return -1;
		}
	}
	
	public int varifyEmails(){
		String[] text = listTA.getText().split("\\n");
		if(text.length < 1 || (text.length == 1 && text[0].length() < 5)){
			System.out.println("List item size is ZERO");
			return 0;
		}
		if (text.length > 0){
		listTA.setText("");
		System.out.println("List item size is : " + text.length);
		LinkedList<String> emails = new LinkedList<>(Arrays.asList(text));
		MailBoxValidator mailBoxValidator = new MailBoxValidator();
		ArrayList<String> validmails = mailBoxValidator.checkEmail(emails);
			if(validmails.size() > 0){
				System.out.println("A valid mail found");
				StringBuilder sb = new StringBuilder();
				for (String string : validmails) {
					sb.append(string + "\n");
				}
				listTA.setText(sb.toString());
			}
		}
		return 0;
	}
	
	public void generateEmail(ActionEvent event){
		String firstName = firstNameTF.getText().toLowerCase();
		String lastName = lastNameTF.getText().toLowerCase();
		String webUrl = webUrlTF.getText().toLowerCase();
		
		EmailPermutator emailPermutator = new EmailPermutator(firstName, lastName, webUrl);
		
		ArrayList<String> permutatemails = emailPermutator.permutator();
		if(permutatemails.size() > 0){
			StringBuilder sb = new StringBuilder();
			for (String string : permutatemails) {
				sb.append(string + "\n");
			}
			listTA.appendText(sb.toString());
		}
	
		System.out.println("" + firstName+ " " + lastName + " "+ webUrl);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		extractProfileInfo = new ExtractProfileInfo();
		chromeOperator = null;
		person = new Person();
		engineProfile = profileWV.getEngine();
		engineLogo = logoWV.getEngine();
		listTA.setText("");
	}
	
}
