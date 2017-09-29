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

import dataprocessor.ExtractProfileInfo;

public class TestRun {
	private static WebDriver driver;
	private static Document doc;
	public static void main(String[] args) {

        /*
        // work on live
		openChromewithSelenium();
        driver.get("https://www.linkedin.com");		
        loginLinkedinAccount();
        System.out.println("login - success");
        */
        
      try {
    	// saved profile page  
  		File input = new File("E:\\Project Findout Linkedin user Email address\\saved pages\\Nabila Tajrin _ LinkedIn.html");
  		//File input = new File("E:\\Project Findout Linkedin user Email address\\saved pages\\Yaron J. Zoller, DBA _ LinkedIn.html");
  		
    	  // saved company page 
    	  //File input = new File("E:\\Project Findout Linkedin user Email address\\saved pages\\Dnet - a social enterprise_ Overview _ LinkedIn.html");
    	  //File input = new File("E:\\Project Findout Linkedin user Email address\\saved pages\\Soliber Net_ Overview _ LinkedIn.html");
    	//File input = new File("E:\\Project Findout Linkedin user Email address\\saved pages\\Nova Southeastern University_ Overview _ LinkedIn.html");
    	//File input = new File("E:\\Project Findout Linkedin user Email address\\saved pages\\Monotype_ Overview _ LinkedIn.html");
  
    	 // saved company list page 
    	//File input = new File("E:\\Project Findout Linkedin user Email address\\saved pages\\company_people_Search _ LinkedIn1.html");
    	//File input = new File("E:\\Project Findout Linkedin user Email address\\saved pages\\(2) Search _ LinkedIn.html");
    	//File input = new File("E:\\Project Findout Linkedin user Email address\\saved pages\\company_people_Search _ LinkedIn1.html");
    	
    	  doc = Jsoup.parse(input, "UTF-8", "https://www.linkedin.com/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  /*
      String pageSource = doc.html(); 
      PeopleList peopleList = new PeopleList("dnet", pageSource);
      peopleList.takeList();
      */
      
      String pageSource = doc.html(); 
      ExtractProfileInfo extractProfileInfo = new ExtractProfileInfo();
      extractProfileInfo.extractInfo(pageSource);
      
    	  
      //companyInfo();
      //takeDatafromProfile();
        System.out.println("- - success - -");
	}
	
	
	private static void takeDatafromCompanyProfileList() {
		/*
		// work in live
        //driver.get("https://www.linkedin.com/------------");
        String pageSource = driver.getPageSource();
        Document doc = Jsoup.parse(pageSource, "https://www.linkedin.com/");
	 	*/
		// Company people list 
        Elements elements = doc.select("div.search-result__wrapper");
                     
        for (Element element : elements) {
        	//Element profilename = element.select("span.name.actor-name").first();
        	Element profilename = element.select("span.actor-name").first();
        	if(profilename!=null) System.out.println("name : " + profilename.text());
        	Element profileanchor = element.select("a.search-result__result-link.ember-view").first();
            String webLink = profileanchor.attr("href");
        	System.out.println("profile : " + webLink);
            Element profileposition = element.select("p.subline-level-1.search-result__truncate").first();
            System.out.println("position : " + profileposition.text());
            Element profilecountry = element.select("p.subline-level-2.search-result__truncate").first();
            System.out.println("country : " + profilecountry.text());
            Element profilecurrent = element.select("V").first();
            if(profilecurrent!=null) System.out.println("Current : " + profilecurrent.text());
            System.out.println(" --  --  -- -- -- --");


		}
		
	}

	public static void openChromewithSelenium(){
		/*
		System.setProperty("webdriver.chrome.driver", "../Linkedin Email Hunter/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        */
		/*
		String userProfile= "C:\\Users\\java user\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\";
		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir="+userProfile);
		options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);
        */
		
		System.setProperty("webdriver.chrome.driver","../Linkedin Email Hunter/resources/chromedriver.exe");
        String chromeProfilePath = "C:\\Users\\java user\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\";
        //String chromeProfilePath = "C:/Users/java user/AppData/Local/Google/Chrome/User Data";
        ChromeOptions chromeProfile = new ChromeOptions();
        chromeProfile.addArguments("chrome.switches", "--disable-extensions");
        chromeProfile.addArguments("user-data-dir=" + chromeProfilePath);

        driver = new ChromeDriver(chromeProfile);

		 
		/*
        System.setProperty("webdriver.chrome.driver","../Linkedin Email Hunter/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:/Users/java user/AppData/Local/Google/Chrome/User Data");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        */

	}
	
	
	public static void loginLinkedinAccount(){
		/*
        driver.findElement(By.cssSelector("input#login-email.login-email")).sendKeys("rezatrue@yahoo.com");
        driver.findElement(By.cssSelector("input#login-password.login-password")).sendKeys("1Canada12");
        */
		driver.findElement(By.cssSelector("input#login-email.login-email")).sendKeys("faysal.uddin@mail.ru");
        driver.findElement(By.cssSelector("input#login-password.login-password")).sendKeys("sj99991212");
        driver.findElement(By.cssSelector("input#login-submit.login.submit-button")).click();

	}
	
	public static void openCompanyProfilePage(){
        driver.get("https://www.linkedin.com/company/1684787/");		
		// we can also click on image    
	}

	public static void takeDatafromCompanyProfile(){
		/*
		// work in live
        //driver.get("https://www.linkedin.com/company/1684787/"); // Dnet
        driver.get("https://www.linkedin.com/company/2317555/");   // Soliber
        String pageSource = driver.getPageSource();
        Document doc = Jsoup.parse(pageSource, "https://www.linkedin.com/");
	 	*/ 
		// Company url 
        Element webdiv = doc.select("div.org-about-company-module__company-page-url.truncate.mb3").first();
        Element webanchor = webdiv.select("a.org-about-us-company-module__website.mb3.link-without-visited-state.ember-view").first();
        String webLink = webanchor.attr("href");
        System.out.println("WebSite : " + webLink);
        
        // open list of profile
        Element comlist = doc.select("a.org-company-employees-snackbar__details-highlight.snackbar-description-see-all-link.link-without-visited-state.ember-view").first();
        String text = comlist.text();
        System.out.println("text : " + text);
        
	}

	public static void companyInfo(){
        
        // Name
		Element eName = doc.select("h1.org-top-card-module__name").first();
        //System.out.println("eName : " +  eName);
        System.out.println("eName : " +  eName.text());

        // Website
        Element eWebsite = doc.select("a.org-about-us-company-module__website.mb3.link-without-visited-state.ember-view").first();
        //System.out.println("eWebsite : " +  eWebsite);
        System.out.println("eWebsite : " +  eWebsite.text());

        //Logo
        Elements eLogodiv = doc.select("div.org-top-card-module__container.container-with-shadow.clearfix");

        Element eLogo = eLogodiv.select("img.lazy-image org-top-card-module__logo.loaded").first();
        System.out.println("eLogo : " +  eLogo);
        //System.out.println("eLogo : " +  eLogo.attr("src"));
        
        
        // see all employee
        Element eEmplyePage = doc.select("a.org-company-employees-snackbar__details-highlight.snackbar-description-see-all-link.link-without-visited-state.ember-view").first();
        //System.out.println("eEmplyePage : " +  eEmplyePage);
        System.out.println("eEmplyePage : " +  eEmplyePage.attr("href"));

	}
	public static void takeDatafromProfile(){
		/*
		// work in live
        //driver.get("https://www.linkedin.com/in/nabila-tajrin-187aab84/");
        driver.get("https://www.linkedin.com/in/yaronzoller/");
        String pageSource = driver.getPageSource();
        Document doc = Jsoup.parse(pageSource, "https://www.linkedin.com/");
	 	*/  
		
        // full name
        Element masthead = doc.select("h1.pv-top-card-section__name").first();
        System.out.println("name :" + masthead.text());
        
        // designation with company name
        Element masthead1 = doc.select("h2.pv-top-card-section__headline").first();
        System.out.println("designation :" + masthead1.text());
        
        // profile url   
        Elements profileSection = doc.select("section.pv-contact-info__contact-type.ci-vanity-url");
        
        Element select = profileSection.select("a.pv-contact-info__contact-item.pv-contact-info__contact-link").first();
        String text = select.text(); 
        String link = select.attr("abs:href"); // "http://jsoup.org/"
        System.out.println(text + " : " + link);
        
     // profile public email   
        Elements profileEmail = doc.select("section.pv-contact-info__contact-type.ci-email");
        
        Element selectMail = profileEmail.select("a.pv-contact-info__contact-item.pv-contact-info__contact-link").first();
        String textMail = null;
		String linkMail = null;
		try {
			textMail = selectMail.text(); 
			linkMail = selectMail.attr("abs:href");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(textMail + " : " + linkMail);
        
     // profile image url
        Elements profileImage = doc.select("div.pv-top-card-section__photo-wrapper.EntityPhoto-circle-8");
        
        Element selectImage = profileImage.select("img.pv-top-card-section__image").first();
        // String textMail = selectMail.text(); 
        String linkImage = selectImage.attr("src"); 
        String imageId = linkImage.substring(linkImage.lastIndexOf("/")+1, linkImage.length());
        System.out.println("Image" + " : " + "https://media.licdn.com/mpr/mpr/shrinknp_400_400/"+ imageId);
        
        //https://media.licdn.com/mpr/mpr/shrink_100_100/p/1/000/0ea/035/1a81184.png
        
        Elements exprience = doc.select("div.pv-oc.ember-view");

     // company image url       //#ember2809 #ember2814 #1010065813
        Elements companylist = doc.select("section.pv-profile-section.experience-section.ember-view");
        Elements companylistul = companylist.select("ul.pv-profile-section__section-info.section-info.pv-profile-section__section-info--has-no-more.ember-view");
        Elements companyimgContainer = companylistul.select("div.pv-profile-section__sortable-card-item.pv-position-entity.ember-view");

        //Element companyanckor = exprience.select("a.ember-view").first();
        Element companyanckor = exprience.select("div.pv-profile-section__sortable-card-item.pv-position-entity.ember-view > a.ember-view").first();

    	String compLiskedinPage = companyanckor.attr("abs:href");
    	System.out.println("compLiskedinPage  " +compLiskedinPage);
        Element companyImage = exprience.select("img.lazy-image.pv-entity__logo-img.pv-entity__logo-img.EntityPhoto-square-5.loaded").first();

        String compName = null;
		String compImage = null;
		try {
			compName = companyImage.attr("alt"); 
			compImage = companyImage.attr("src");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        System.out.println("Company Name : " + compName + " : " + "Image : " + compImage );

        String compimageId = compImage.substring(compImage.lastIndexOf("/")+1, compImage.length());
        System.out.println("Company Image" + " : " + "https://media.licdn.com/mpr/mpr/shrinknp_400_400/"+ compimageId);


	}
	

}
