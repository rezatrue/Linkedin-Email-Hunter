package webhandler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import data.Company;


public class ExtractCompanyInfo {
	private Company company;
	
	public ExtractCompanyInfo() {
		company = new Company();
	}
	
	public Company extractInfo(String pageSource){
		
        Document doc = Jsoup.parse(pageSource, "https://www.linkedin.com/");

		// Name
		Element eName = doc.select("h1.org-top-card-module__name").first();
        //System.out.println("eName : " +  eName);
        System.out.println("eName : " +  eName.text());
        company.setCompanyName(eName.text());
        
        // Website
        Element eWebsite = doc.select("a.org-about-us-company-module__website.mb3.link-without-visited-state.ember-view").first();
        //System.out.println("eWebsite : " +  eWebsite);
        System.out.println("eWebsite : " +  eWebsite.text());
        company.setCompanyWebsite(eWebsite.text());
        //Logo
        Elements eLogodiv = doc.select("div.org-top-card-module__container.container-with-shadow.clearfix");

        Element eLogo = eLogodiv.select("img.lazy-image org-top-card-module__logo.loaded").first();
        System.out.println("eLogo : " +  eLogo);
        //System.out.println("eLogo : " +  eLogo.attr("src"));
        company.setCompanyLogo(eLogo.attr("src"));
        
        // see all employee
        Element eEmplyePage = doc.select("a.org-company-employees-snackbar__details-highlight.snackbar-description-see-all-link.link-without-visited-state.ember-view").first();
        //System.out.println("eEmplyePage : " +  eEmplyePage);
        System.out.println("eEmplyePage : " +  eEmplyePage.attr("href"));
		company.setCompanyPeopleListlink(eEmplyePage.attr("href"));
		return company;
	}

	
	

}
