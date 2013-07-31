package baseClasses;

public class Company {
	private boolean good;
	private String companyName, typeOfCompany;
	private int uniqueID;
	
	public Company(boolean good, String companyName, int uniqueID) {
		super();
		setGood(good);
		setCompanyName(companyName);
		setUniqueID(uniqueID);
	}
	

	//GETTERS
	public boolean isGood() {
		return good;
	}

	public String getCompanyName() {
		return companyName;
	}


	public int getUniqueID() {
		return uniqueID;
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

	public void setUniqueID(int uniqueID){
		this.uniqueID = uniqueID;
	}
	
	public void setTypeOfCompany(String typeOfCompany){
		this.typeOfCompany = typeOfCompany;
	}


}
