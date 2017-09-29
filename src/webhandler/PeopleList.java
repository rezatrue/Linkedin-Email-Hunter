package webhandler;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PeopleList {
	private String company;
	private String pageSource;
	private ArrayList<String> profileLinks;
	
	public PeopleList(String company, String pageSource){
		this.company = company;
		this.pageSource = pageSource;
		profileLinks = new ArrayList<>();
		
	}
	
	public ArrayList<String> takeList(){
		System.out.println("--1--");

        Document doc = Jsoup.parse(pageSource, "https://www.linkedin.com/");
        
		Elements listcontainer = doc.select("div.search-results.ember-view");
		Elements liste = listcontainer.select("div.search-result__wrapper");
	
		for (Element element : liste) {
			String com = element.select("p.search-result__snippets.mt2.ember-view").text();
			System.out.println(com +" ---  ");
	
			//if(com.toLowerCase().contains(company)){
				String url = element.select("a.search-result__result-link.ember-view").attr("href");
				profileLinks.add(url);
				System.out.println(url);
			//}
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
