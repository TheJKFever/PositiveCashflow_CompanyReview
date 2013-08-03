package baseClasses;

import java.io.Serializable;

/**
 * @author Jeroen Goossens & Jon Koehmstedt
 * CSC202 Final Project
 */
public class Company implements Serializable, Comparable<Company> {
	/*   
	 * 	Company isGood Key: 0 = unknown, 1 = good, 2 = bad
	 */
	
	private static final long serialVersionUID = 5854496261816208067L;
	protected int isGood;
	protected String companyName, typeOfCompany;
	
	public Company(int good, String companyName, String type) {
		super();
		setGood(good);
		setCompanyName(companyName);
		this.setTypeOfCompany(type);
	}	

	//GETTERS
	public int isGood() {
		return isGood;
	}

	public String getCompanyName() {
		return companyName;
	}
	
	public String getTypeOfCompany(){
		return typeOfCompany;
	}

	//SETTERS
	public void setGood(int good) {
		this.isGood = good;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setTypeOfCompany(String typeOfCompany){
		this.typeOfCompany = typeOfCompany;
	}

	@Override
	public int compareTo(Company coPany) {
		if (this.getCompanyName().toLowerCase().compareTo(coPany.getCompanyName().toLowerCase())<0) {
			return -1;
		} else if (this.getCompanyName().toLowerCase().compareTo(coPany.getCompanyName().toLowerCase())>0){
			return 1;
		} else {
		return 0;
		}
	}

}

