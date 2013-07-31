package frontend;

import baseClasses.*;

public class CompanyFE extends Company{
	private SortedLL<Transaction> transactionList;
	
	public CompanyFE(boolean good, String companyName, int uniqueID) {
		super(good, companyName, uniqueID);
		transactionList = new SortedLL<Transaction>();
	}

	//GETTERS
	public SortedLL<Transaction> getTransactionList() {
		return transactionList;
	}

	//SETTERS
	public void addTransaction(Transaction t){
		transactionList.add(t);
	}

}
