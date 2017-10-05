package socialmedia;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TwitterSearch {
	
	public TwitterSearch() {
	}

	public void serach(String name){
		//System.out.println("Twitter Hello -- "+ name);
		try {
			Document doc = Jsoup.connect("https://twitter.com/search?f=users&vertical=default&q=ali%20reza&src=typd").get();
			
			Elements elements = doc.select("div.ProfileCard.js-actionable-user");
			
			for (Element element : elements) {
				String image = element.select("img.ProfileCard-avatarImage.js-action-profile-avatar").attr("src");
				System.out.println(image);
				String fullname = element.select("a.fullname.ProfileNameTruncated-link.u-textInheritColor.js-nav").text().toString();
				System.out.println(fullname);
				String profile = element.select("a.fullname.ProfileNameTruncated-link.u-textInheritColor.js-nav").attr("href");
				System.out.println(profile);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
}
