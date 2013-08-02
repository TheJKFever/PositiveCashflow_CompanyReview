package baseClasses;

import java.io.Serializable;

/**
 * @author Jeroen Goossens & Jon Koehmstedt
 * CSC202 Final Project
 */
public class Company implements Serializable{//, Comparable<Company>{

	private static final long serialVersionUID = -8698835401119697249L;
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

//	@Override
//	public int compareTo(Company coPany) {
//		if (this.getCompanyName().compareTo(coPany.getCompanyName())<0) {
//			return -1;
//		} else if (this.getCompanyName().compareTo(coPany.getCompanyName())>0){
//			return 1;
//		} else {
//		return 0;
//		}
//	}


}

