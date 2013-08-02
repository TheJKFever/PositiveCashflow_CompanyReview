package frontend;

import baseClasses.*;
/**
 * 
 * @author Jeroen Goossens & Jon Koehmstedt
 * CSC202 Final Project
 */
public class CompanyFE extends Company{

	private static final long serialVersionUID = 6275261625641986745L;
	private SortedLL<Transaction> transactionList;
	private double total=0;
	
	public CompanyFE(boolean good, String companyName, String companyType) {
		super(good, companyName, companyType);
		transactionList = new SortedLL<Transaction>();
	}
	
	//GETTERS
	public SortedLL<Transaction> getTransactionList() {
		return transactionList;
	}

	public double getTotal() {
		return total;
	}

	//SETTERS
	public void addTransaction(Transaction t){
		transactionList.add(t);
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String toString(){
		String output = "";
		output+=companyName+" ("+typeOfCompany+"): "+good+"\n";
		
		
		return output;
	}

}
