package socialmedia;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TwitterSearch {
	
	public TwitterSearch() {
	}

	public String serach(String fname, String lname){
		HashMap<String, String> list = new HashMap<>();
		//System.out.println("Twitter Hello -- "+ name);
		
		StringBuilder sb = new StringBuilder("<!DOCTYPE html>" + "\n");
		sb.append("<head>" + "\n");
		sb.append("<meta charset=utf-8>" + "\n");
		sb.append("<title>Select the user</title>" + "\n");
		sb.append("</head>" + "\n");
		sb.append("<body>" + "\n");
		sb.append("<h3>" + " Select Image " + "</h3>" +"\n");
		
		
		try {
			Document doc = Jsoup.connect("https://twitter.com/search?f=users&vertical=default&q="+fname+"%20"+lname+"&src=typd").get();
			
			Elements elements = doc.select("div.ProfileCard.js-actionable-user");
			
			for (Element element : elements) {
				String image = element.select("img.ProfileCard-avatarImage.js-action-profile-avatar").attr("src");
				//System.out.println(image);
				String fullname = element.select("a.fullname.ProfileNameTruncated-link.u-textInheritColor.js-nav").text().toString();
				//System.out.println(fullname);
				String profile = element.select("a.fullname.ProfileNameTruncated-link.u-textInheritColor.js-nav").attr("abs:href");
				//System.out.println(profile);
				//list.put(fullname, image);
				sb.append("<a href='"+profile +"'>" + "\n");
				sb.append("<img src='"+ image +"'>" + "\n");	
				sb.append("<b>"+fullname +"</b>" + "\n");
				sb.append("</a>" + "\n");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sb.append("</body>" + "\n");
	    sb.append("</html>" + "\n");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	
}
