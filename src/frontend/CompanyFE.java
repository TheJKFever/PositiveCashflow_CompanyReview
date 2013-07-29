package frontend;

import baseClasses.*;

public class CompanyFE extends Company{
	private boolean good;
	private String companyName;
	private int uniqueID;
	private SortedLL<Transaction> transactionList;
			
	
	public CompanyFE(boolean good, String companyName, int uniqueID) {
		super(good, companyName, uniqueID);
		transactionList = new SortedLL<Transaction>();
	}


	public boolean isGood() {
		return good;
	}


	public String getCompanyName() {
		return companyName;
	}


	public int getUniqueID() {
		return uniqueID;
	}


	public SortedLL<Transaction> getTransactionList() {
		return transactionList;
	}


	public void setGood(boolean good) {
		this.good = good;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}


	public void setTransactionList(SortedLL<Transaction> transactionList) {
		this.transactionList = transactionList;
	}
	

}
