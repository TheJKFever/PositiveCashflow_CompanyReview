package frontend;

import baseClasses.*;
/**
 * 
 * @author Jeroen Goossens & Jon Koehmstedt
 * CSC202 Final Project
 */
public class CompanyFE extends Company{
	private SortedLL<Transaction> transactionList;
	
	public CompanyFE(boolean good, String companyName, String companyType) {
		super(good, companyName, companyType);
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
	
	public String toString(){
		String output = "";
		output+=companyName+" ("+typeOfCompany+"): "+good+"\n";
		
		
		return output;
	}

}
