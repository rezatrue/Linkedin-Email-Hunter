package dataprocessor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import data.Person;

public class ExtractProfileInfo {
	Person person;

	public ExtractProfileInfo() {
		person = new Person();
	}
	
	public Person extractInfo(String pageSource){
		
        Document doc = Jsoup.parse(pageSource, "https://www.linkedin.com/");
	 	//System.out.println(pageSource); 
        
		
        // full name
        Element fullname = doc.select("h1.pv-top-card-section__name").first();
        String name = fullname.text();
        if(name.contains(",")) name = name.substring(0, name.indexOf(","));
        String names[] = name.split(" ");
        String firstname = names[0]; 
        person.setFirstname(firstname);
        String lastname = names[names.length-1]; 
        person.setLastname(lastname);

        //System.out.println("name :" + firstname + " " +lastname);
        
        String company = "";
        String designation = "";
        // designation with company name
        Element position = doc.select("h2.pv-top-card-section__headline").first();
        String companydesignation = position.text();
        //System.out.println(companydesignation);
        if (companydesignation.toLowerCase().contains(" at")){
        	designation = companydesignation.substring(0, companydesignation.indexOf(" at"));
        	company = companydesignation.substring(companydesignation.indexOf(" at")+3, companydesignation.length());
        }else if (companydesignation.toLowerCase().contains(",")){
        	designation = companydesignation.substring(0, companydesignation.indexOf(","));
        	company = companydesignation.substring(companydesignation.indexOf(",")+1, companydesignation.length());  
        }
        person.setDesignation(designation);
        //System.out.println("designation :" + designation);
        //System.out.println("company :" + company);

        
        // profile url   
        String profileUrl = "";
		try {
			Elements profileSection = doc.select("section.pv-contact-info__contact-type.ci-vanity-url");
			Element select = profileSection.select("a.pv-contact-info__contact-item.pv-contact-info__contact-link").first();
			profileUrl = select.attr("abs:href"); // "http://jsoup.org/"
		} catch (Exception e) {
	        System.err.println("error :" + e.getMessage());
		}
		person.setProfileUrl(profileUrl);
        //System.out.println("profile link" + " : " + profileUrl);

		// website   
        String website = "";
		try {
			Elements websiteSection = doc.select("section.pv-contact-info__contact-type.ci-websites");
			Elements websiteList = doc.select("li.pv-contact-info__ci-container");
			Elements select = websiteList.select("a.pv-contact-info__action.pv-contact-info__contact-link");
			System.out.println("-- : " + select.size());
			for (Element element : select) {
				website = website + element.text() + " ";
			}
			website = website.trim();
		} catch (Exception e) {
	        System.err.println("error :" + e.getMessage());
		}
		person.setWebsite(website);
        //System.out.println("websites" + " : " + website);

        
     //String linkMail = selectMail.attr("abs:href"); // "http://jsoup.org/"
	// profile public email  
	String email = "";
	try { 
		Elements profileEmail = doc.select("section.pv-contact-info__contact-type.ci-email");
        Elements selectMail = profileEmail.select("a.pv-contact-info__contact-item.pv-contact-info__contact-link");
        String textMail = "";
        for (Element element : selectMail) {
        	textMail = textMail + element.text() + " ";
		}
        email = textMail.trim();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     person.setEmail(email);   
     System.out.println("email" + " : " + email);
   
        
     // profile image url
     String image = "";
        Elements profileImage = doc.select("div.pv-top-card-section__photo-wrapper.EntityPhoto-circle-8");
        Element selectImage = profileImage.select("img.pv-top-card-section__image").first();
        image = selectImage.attr("src"); 
        person.setImage(image);
        //System.out.println("Image" + " : " + image);
        
        //https://media.licdn.com/mpr/mpr/shrink_100_100/p/1/000/0ea/035/1a81184.png
    String compLiskedinPage = "";    
	String companylogo = "";
	try {
		// company image url
        Elements exprience = doc.select("div.pv-oc.ember-view");

		/*
		    //Elements companylist = doc.select("section.pv-profile-section.experience-section.ember-view");
		    Elements companysection = doc.select("section.pv-profile-section.experience-section.ember-view");
		    //Elements companylistul = companylist.select("ul.pv-profile-section__section-info.section-info.pv-profile-section__section-info--has-more.ember-view");
		    Elements companylist = companysection.select("ul.pv-profile-section__section-info.section-info.pv-profile-section__section-info--has-more.ember-view");
		    Elements companyitem = companylist.select("li.pv-profile-section__sortable-item.pv-profile-section__section-info-item.relative sortable-item.ember-view");
		    //Elements companyimgContainer = companylistul.select("div.pv-profile-section__sortable-card-item.pv-position-entity.ember-view");
		    Elements companycontainer = companyitem.select("div.pv-profile-section__sortable-card-item.pv-position-entity.ember-view");
		    
		    Elements companydiv = companyanckor.select("div.pv-entity__logo.company-logo");
		    //Element companyImage = companyimgContainer.select("img.lazy-image.pv-entity__logo-img.pv-entity__logo-img.EntityPhoto-square-5.loaded").first();
		    */
	    	//Element companyanckor = exprience.select("a.ember-view").first();
	        Element companyanckor = exprience.select("div.pv-profile-section__sortable-card-item.pv-position-entity.ember-view > a.ember-view").first();

	    	
        	compLiskedinPage = companyanckor.attr("abs:href"); 
        	//System.out.println(" company Liskedin Page : " + compLiskedinPage);
		    Element companyImage = exprience.select("img.lazy-image.pv-entity__logo-img.pv-entity__logo-img.EntityPhoto-square-5.loaded").first();  
		    //System.out.println(" company Image : " + companyImage);
		    
		    companylogo = companyImage.attr("src"); 
		    if(company!="") company = companyImage.attr("alt"); 
		    
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    person.setCompanylinkedinpage(compLiskedinPage);  
    person.setCompanylogo(companylogo);  
    person.setCompany(company);  
    //System.out.println("compLiskedinPage" + " : " + compLiskedinPage);
    //System.out.println("companylogo" + " : " + companylogo);
    //System.out.println("company" + " : " + company);

     
      return person;
	}
	
	
}
