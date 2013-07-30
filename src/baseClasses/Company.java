package baseClasses;

public class Company {
	private boolean good;
	private String companyName;
	private int uniqueID;

	public Company(){
		
	}
	
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

	//SETTERS
	private void setGood(boolean good) {
		this.good = good;
	}

	private void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	private void setUniqueID(int uniqueID){
		this.uniqueID = uniqueID;
	}


}
