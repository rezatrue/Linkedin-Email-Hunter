package dataprocessor;

import java.util.ArrayList;
import java.util.List;

public class EmailPermutator {
	private ArrayList<String> list; 
	
	private String firstName;
	private String lastName;
	private String url;

	public EmailPermutator(String firstName, String lastName, String url) {
		list = new ArrayList<>();
		this.firstName = firstName;
		this.lastName = lastName;
		// https://stackoverflow.com/questions/16673628/remove-url-prefix-from-string-http-www-etc
		this.url = url.replaceFirst("^(http://www\\.|http://|www\\.|https://www\\.|https://|www\\.)","");		
	}
	

	public ArrayList<String> permutator(){
		list.add(firstName + "@" + url);
		list.add(lastName + "@" + url);
		list.add(firstName + lastName + "@" + url);
		list.add(firstName +"."+ lastName + "@" + url);
		list.add(firstName.substring(0, 1) + lastName + "@" + url);
		list.add(firstName.substring(0, 1) +"."+ lastName + "@" + url);
		list.add(firstName+"_"+ lastName + "@" + url);
		list.add(firstName.substring(0, 1) +"_"+ lastName + "@" + url);
		list.add(firstName+"-"+ lastName + "@" + url);
		list.add(firstName.substring(0, 1) +"-"+ lastName + "@" + url);
		
		return list;
		
	}
	
	// can generate lot of combination but those are not realistic
	public void addString (String first, String last){
		list.add(first + "." + last + "@" + url);
		list.add(last + "." + first + "@" + url);
		list.add(first + last + "@" + url);
		list.add(last + first + "@" + url);
		list.add(last +"_"+ first + "@" + url);
		list.add(last +"-"+ first + "@" + url);
	}
	
	
	
	
}
