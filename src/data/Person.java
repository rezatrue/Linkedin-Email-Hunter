package data;

public class Person {
	private String firstname;
	private String lastname;
	private String image;
	private String email;
	private String profileUrl;
	private String designation;
	private String company;
	private String website;
	private String companylogo;

	public Person() {
	}
	
	
	public Person(String firstname, String lastname, String image, String email, String profileUrl, String designation,
			String company, String website, String companylogo) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.image = image;
		this.email = email;
		this.profileUrl = profileUrl;
		this.designation = designation;
		this.company = company;
		this.website = website;
		this.companylogo = companylogo;
	}





	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCompanylogo() {
		return companylogo;
	}

	public void setCompanylogo(String companylogo) {
		this.companylogo = companylogo;
	}


	@Override
	public String toString() {
		System.out.println("firstname = " + firstname + ", lastname = " + lastname + ", image = " + image + ", email=" + email
				+ ", profileUrl = " + profileUrl + ", designation = " + designation + ", company = " + company + ", website = "
				+ website + ", companylogo = " + companylogo);
		return "Person [firstname=" + firstname + ", lastname=" + lastname + ", image=" + image + ", email=" + email
				+ ", profileUrl=" + profileUrl + ", designation=" + designation + ", company=" + company + ", website="
				+ website + ", companylogo=" + companylogo + "]";
	}
	
	
	
	
	
	
//	URL -- 
//	name :Nabila Tajrin
//	designation :Trainee Developer at Dnet
//	linkedin.com/in/nabila-tajrin-187aab84 : https://www.linkedin.com/in/nabila-tajrin-187aab84
//	nabilatajrin@gmail.com : mailto:nabilatajrin@gmail.com
//	Image : https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAAyTAAAAJDI1OTEyNTI5LWFjYzItNDZhMi1iZWVjLWUwYTYzOTFiNTkxMA.jpg
//	Company Name : Dnet - a social enterprise : Image : ./Nabila Tajrin _ LinkedIn_files/1d500c6.png
//	Company Image : https://media.licdn.com/mpr/mpr/shrinknp_400_400/1d500c6.png

	
	
	
}
