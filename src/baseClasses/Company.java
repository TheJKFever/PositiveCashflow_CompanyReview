package baseClasses;
/**
 * 
 * @author Jeroen Goossens & Jon Koehmstedt
 * CSC202 Final Project
 */
public class Company {
	protected boolean good;
	protected String companyName, typeOfCompany;
	
	public Company(boolean good, String companyName, String type) {
		super();
		setGood(good);
		setCompanyName(companyName);
		this.setTypeOfCompany(type);
	}	

	//GETTERS
	public boolean isGood() {
		return good;
	}

	public String getCompanyName() {
		return companyName;
	}
	
	public String getTypeOfCompany(){
		return typeOfCompany;
	}

	//SETTERS
	public void setGood(boolean good) {
		this.good = good;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setTypeOfCompany(String typeOfCompany){
		this.typeOfCompany = typeOfCompany;
	}

}
