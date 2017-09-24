package webhandler;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import data.Person;

public class ChromeOperator {
	private WebDriver driver;
	private Document doc;
	
	public ChromeOperator() {
	}
	
	public String openBrowserLogin(){
		if(!openChromewithSelenium())
			return "Unable to Open Browser";
		else {
			driver.get("https://www.linkedin.com");		
			if(!loginLinkedinAccount())
				return "Unable to Login";
		}
        System.out.println("login - success");
        return "";
	}
	
	private boolean openChromewithSelenium(){
		/*
		System.setProperty("webdriver.chrome.driver", "../Linkedin Email Hunter/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        */
		
		try {
			System.setProperty("webdriver.chrome.driver","../Linkedin Email Hunter/resources/chromedriver.exe");
			String chromeProfilePath = "C:\\Users\\java user\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\";
			//String chromeProfilePath = "C:/Users/java user/AppData/Local/Google/Chrome/User Data";
			ChromeOptions chromeProfile = new ChromeOptions();
			chromeProfile.addArguments("chrome.switches", "--disable-extensions");
			chromeProfile.addArguments("user-data-dir=" + chromeProfilePath);
			driver = new ChromeDriver(chromeProfile);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
        
        
		}

	private boolean loginLinkedinAccount(){
		
		try {
			driver.findElement(By.cssSelector("input#login-email.login-email")).sendKeys("rezatrue@yahoo.com");
	        driver.findElement(By.cssSelector("input#login-password.login-password")).sendKeys("1Canada12");
//			driver.findElement(By.cssSelector("input#login-email.login-email")).sendKeys("faysal.uddin@mail.ru");
//			driver.findElement(By.cssSelector("input#login-password.login-password")).sendKeys("sj99991212");
			driver.findElement(By.cssSelector("input#login-submit.login.submit-button")).click();
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}

	}

	public String openProfile(String purl){
        driver.get(purl);

        driver.findElement(By.cssSelector("button.contact-see-more-less.link-without-visited-state")).click();
        //String yourtext= driver.findElement(By.tagName("body")).getText() ;
        String pageSource = driver.getPageSource();

		return pageSource;
	}
	
	
}
