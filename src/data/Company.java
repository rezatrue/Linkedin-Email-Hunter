package data;

import java.util.ArrayList;
import java.util.Iterator;

public class Company {
	
	private String companyName;
	private String companyWebsite;
	private String companyLogo;
	private String companyPeopleListlink;
	private ArrayList<Person> companyEmplyee;
	
	public Company() {
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	
	
	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getCompanyPeopleListlink() {
		return companyPeopleListlink;
	}

	public void setCompanyPeopleListlink(String companyPeopleListlink) {
		this.companyPeopleListlink = companyPeopleListlink;
	}

	public ArrayList<Person> getCompanyEmplyee() {
		return companyEmplyee;
	}

	public void setCompanyEmplyee(Person person) {
		this.companyEmplyee.add(person);
	}

	@Override
	public String toString() {
		Iterator it = companyEmplyee.iterator();
		while(it.hasNext()){
			Person person = (Person) it.next();
			System.out.println(person.getFirstname() + " " + person.getLastname() +" : " + person.getEmail());
		}
		
		return "Company [companyName=" + companyName + ", companyWebsite=" + companyWebsite + ", companyPeopleListlink="
				+ companyPeopleListlink + ", companyEmplyee=" + companyEmplyee + "]";
	}

	
	
	

}
