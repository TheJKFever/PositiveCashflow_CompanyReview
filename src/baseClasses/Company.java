package baseClasses;

public class Company {
	private boolean good;
	private String companyName;
	private int uniqueID;

	public Company(boolean good, String companyName, int uniqueID) {
		super();
		this.good = good;
		this.companyName = companyName;
		this.uniqueID = uniqueID;
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
	
	//SETTERS
	public void setGood(boolean good) {
		this.good = good;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	
}
