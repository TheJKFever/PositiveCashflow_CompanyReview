package frontend;

import baseClasses.Company;

public class CompanyFE extends Company {
//	private boolean good;
//	private String companyName;
//	private int uniqueID;
	private LinkedList<Transaction> transactionList;;
			
	
	public CompanyFE(boolean good, String companyName, int uniqueID) {
		super(good, companyName, uniqueID);
		transactionList = new LinkedList<Transaction>()

	}
	

}
