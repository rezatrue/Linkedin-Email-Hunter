package webhandler;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import data.Person;

public class ChromeOperator {
	private WebDriver driver;
	private Document doc;
	
	public ChromeOperator() {
	}
	

	public boolean openChromewithSelenium(){
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

	public boolean loginLinkedinAccount(){
		
		try {
//			driver.findElement(By.cssSelector("input#login-email.login-email")).sendKeys("bennye45@oaudienceij.com");
//	        driver.findElement(By.cssSelector("input#login-password.login-password")).sendKeys("sj99991212");
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

	public String openpage(String url){
		driver.get(url);
		waitForPageLoad();
		return ""; 
	}
	
	
//	public String openCompanypage(String url){
//		driver.get(url);
//		
//		return ""; //driver.findElement(By.cssSelector("a.org-about-us-company-module__website.mb3.link-without-visited-state.ember-view")).getAttribute("href").toString();
//	}
	
	public String companyEmplyeListPage(){
		driver.findElement(By.cssSelector("a.org-company-employees-snackbar__details-highlight.snackbar-description-see-all-link.link-without-visited-state.ember-view")).click();
		return ""; 
		}
	
	public String takeListSource(){
		
		driver.findElement(By.cssSelector("a.org-company-employees-snackbar__details-highlight.snackbar-description-see-all-link.link-without-visited-state.ember-view")).click();
		waitForPageLoad();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scrollBy(0,1050)");
		js.executeScript("scrollBy(0,1150)");
		js.executeScript("scrollBy(0,1250)");
		js.executeScript("scrollBy(0,1350)");
		js.executeScript("scrollBy(0,1450)");
		waitForPageLoad();
        String pageSource = driver.getPageSource();

		return pageSource; 
		}
	
	public String nextEmplyeeListPage(){
		driver.findElement(By.cssSelector("button.next")).click();
		return ""; 
		}

		
	
	
	public String openProfile(String purl){
		driver.get(purl);
		
        driver.findElement(By.cssSelector("button.contact-see-more-less.link-without-visited-state")).click();
		// if this don't perfrom well try saving page first
        // this is not full profile way try savaing the page first
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scrollBy(0,850)");
		waitForPageLoad();
		js.executeScript("scrollBy(0,1050)");
		waitForPageLoad();

        //String yourtext= driver.findElement(By.tagName("body")).getText() ;
        
        String pageSource = driver.getPageSource();

		return pageSource;
	}

	private void waitForPageLoad() {
		// https://stackoverflow.com/questions/5868439/wait-for-page-load-in-selenium
	    Wait<WebDriver> wait = new WebDriverWait(driver, 30);
	    wait.until(new Function<WebDriver, Boolean>() {
	        public Boolean apply(WebDriver driver) {
	            System.out.println("Current Window State       : "
	                + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
	            return String
	                .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
	                .equals("complete");
	        }
	    });
	}

	public void close() {
		driver.close();
	}
	
	
}
