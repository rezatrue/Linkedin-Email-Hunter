package webhandler;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EmployeList {
	private String company;
	private String pageSource;
	private ArrayList<String> profileLinks;
	
	public EmployeList(String company, String pageSource){
		this.company = company.toLowerCase().trim();
		this.pageSource = pageSource;
		profileLinks = new ArrayList<>();
		
	}
	
	public ArrayList<String> takeList(){
        Document doc = Jsoup.parse(pageSource, "https://www.linkedin.com/");
		Elements listcontainer = doc.select("div.search-results.ember-view");
		Elements liste = listcontainer.select("div.search-result__wrapper");
	
		for (Element element : liste) {

			String comName = element.select("p.search-result__snippets.mt2.ember-view").text();
			String designation = element.select("p.subline-level-1.search-result__truncate").text();
			
			System.out.println(comName +" ---  ");
			// if company named match than we will add link to the list
			if(comName.toLowerCase().contains(company) || designation.toLowerCase().contains(company)){

				String url = element.select("a.search-result__result-link.ember-view").attr("abs:href");
				System.out.println(url);
				// https://www.linkedin.com/search/results/people/?facetCurrentCompany=%5B%221684787%22%5D#
				// above type of link are Linkedin Members & don't represent any specific people profile page 
				if(url.toLowerCase().contains("https://www.linkedin.com") && !url.toLowerCase().contains("search/results/people/?"))
					{ profileLinks.add(url); System.out.println("-- added --"); }		
			}
		}
	    	
		return profileLinks;
	}
	
	//       <h3 id="ember3082" class="actor-name-with-
	
	// <div id="ember3048" class="search-results ember-view"><!---->  <div 
	// <div class="search-result__wrapper">
	// <span class="name actor-name">Angelica Zadak</span>
	/* <p id="ember3092" class="search-result__snippets mt2 Sans-13px-black-55% ember-view">Current:
	Administrative Coordinator in Academic Affairs at <strong>Nova</strong> <strong>Southeastern</strong> <strong>University</strong>
	</p>*/
	//<img class="lazy-image loaded" alt="Angelica Zadak" height="56" width="56" src="https://media.licdn.com/mpr/mpr/shrink_100_100/AAEAAQAAAAAAAANsAAAAJGRlZGI0YzlhLTU5NTYtNDQwNC04NzhiLTBhMDQxYzVhZmE1Yg.jpg">
	/*
	 * 
	 * <button class="next" data-ember-action="" data-ember-action-3468="3468">
     *   <div class="next-text">
	 * 
	 * 
	 */
	
	
	
}
