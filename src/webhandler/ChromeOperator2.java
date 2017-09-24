package webhandler;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import data.Person;

public class ChromeOperator2 {
	private WebDriver driver;
	private Document doc;
	
	public ChromeOperator2() {
		
		try {
	    	// saved profile page  
	  		File input = new File("E:\\Project Findout Linkedin user Email address\\saved pages\\Nabila Tajrin _ LinkedIn.html");
	  		// saved company page 
	    	//File input = new File("E:\\Project Findout Linkedin user Email address\\saved pages\\Dnet - a social enterprise_ Overview _ LinkedIn.html");
	  		//File input = new File("E:\\Project Findout Linkedin user Email address\\saved pages\\Soliber Net_ Overview _ LinkedIn.html");
	  		// saved company list page 
	    	//File input = new File("E:\\Project Findout Linkedin user Email address\\saved pages\\company_people_Search _ LinkedIn1.html");
	  		
	    	  doc = Jsoup.parse(input, "UTF-8", "https://www.linkedin.com/");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void openChromewithSelenium(){
		/*
		System.setProperty("webdriver.chrome.driver", "../Linkedin Email Hunter/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        */
		
		System.setProperty("webdriver.chrome.driver","../Linkedin Email Hunter/resources/chromedriver.exe");
        String chromeProfilePath = "C:\\Users\\java user\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\";
        //String chromeProfilePath = "C:/Users/java user/AppData/Local/Google/Chrome/User Data";
        ChromeOptions chromeProfile = new ChromeOptions();
        chromeProfile.addArguments("chrome.switches", "--disable-extensions");
        chromeProfile.addArguments("user-data-dir=" + chromeProfilePath);

        driver = new ChromeDriver(chromeProfile);

		}

	public Person takeDatafromProfile(){
		/*
		// work in live
        //driver.get("https://www.linkedin.com/in/nabila-tajrin-187aab84/");
        driver.get("https://www.linkedin.com/in/yaronzoller/");
        String pageSource = driver.getPageSource();
        Document doc = Jsoup.parse(pageSource, "https://www.linkedin.com/");
	 	*/  
		
        // full name
        Element fullname = doc.select("h1.pv-top-card-section__name").first();
        String name = fullname.text();
        String names[] = name.split(" ");
        String frstname = names[0]; 
        String lastname = names[names.length-1]; 
        System.out.println("name :" + frstname + lastname);
        
        // designation with company name
        Element position = doc.select("h2.pv-top-card-section__headline").first();
        String designation = position.text();
        System.out.println("designation :" + designation);
        
        // profile url   
        Elements profileSection = doc.select("section.pv-contact-info__contact-type.ci-vanity-url");
        Element select = profileSection.select("a.pv-contact-info__contact-item.pv-contact-info__contact-link").first();
        String text = select.text(); 
        String link = select.attr("abs:href"); // "http://jsoup.org/"
        String profileUrl = link;
        System.out.println(text + " : " + profileUrl);
        
     // profile public email   
        Elements profileEmail = doc.select("section.pv-contact-info__contact-type.ci-email");
        Elements selectMail = profileEmail.select("a.pv-contact-info__contact-item.pv-contact-info__contact-link");
        String textMail = "";
        for (Element element : selectMail) {
        	textMail = textMail + element.text() + " ";
		}
        String email = textMail.trim();

        //String linkMail = selectMail.attr("abs:href"); // "http://jsoup.org/"
        System.out.println(textMail + " : " + email);
              
        
     // profile image url
        Elements profileImage = doc.select("div.pv-top-card-section__photo-wrapper.EntityPhoto-circle-8");
        Element selectImage = profileImage.select("img.pv-top-card-section__image").first();
        // String textMail = selectMail.text(); 
        String linkImage = selectImage.attr("src"); 
        String imageId = linkImage.substring(linkImage.lastIndexOf("/")+1, linkImage.length());
        String image = "https://media.licdn.com/mpr/mpr/shrinknp_400_400/"+ imageId;
        System.out.println("Image" + " : " + image);
        
        //https://media.licdn.com/mpr/mpr/shrink_100_100/p/1/000/0ea/035/1a81184.png
        
     // company image url
        Elements companylist = doc.select("section#ember2809.pv-profile-section.experience-section.ember-view");
        Elements companylistul = companylist.select("ul#ember2814.pv-profile-section__section-info.section-info.pv-profile-section__section-info--has-no-more.ember-view");
        Elements companyimgContainer = companylistul.select("div#1010065813.pv-profile-section__sortable-card-item.pv-position-entity.ember-view");

        Element companyImage = companyimgContainer.select("img.lazy-image.pv-entity__logo-img.pv-entity__logo-img.EntityPhoto-square-5.loaded").first();

        String compName = companyImage.attr("alt"); 
        String compImage = companyImage.attr("src"); 
        String company = compName;
        System.out.println("Company Name : " + compName + " : " + "Image : " + compImage );

        String compimageId = compImage.substring(compImage.lastIndexOf("/")+1, compImage.length());
        String companylogo = "https://media.licdn.com/mpr/mpr/shrinknp_400_400/"+ compimageId; // live a kisui add kora lagbe na page link thakbe
        System.out.println("Company Image" + " : " + companylogo);
        return new Person();
	}
	
	
}
