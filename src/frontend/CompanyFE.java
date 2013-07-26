package frontend;

import baseClasses.*;

public class CompanyFE extends Company{
//	private boolean good;
//	private String companyName;
//	private int uniqueID;
	private SortedLL<Transaction> transactionList;
			
	
	public CompanyFE(boolean good, String companyName, int uniqueID) {
		super(good, companyName, uniqueID);
		transactionList = new SortedLL<Transaction>();
	}
	

}
