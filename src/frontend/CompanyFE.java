package frontend;

import baseClasses.*;
/**
 * 
 * @author Jeroen Goossens & Jon Koehmstedt
 * CSC202 Final Project
 */
public class CompanyFE extends Company implements Comparable<Company> {

	private static final long serialVersionUID = 735691711181453767L;
	private SortedLL<Transaction> transactionList;
	private double total;
	
	public CompanyFE(int good, String companyName, String companyType) {
		super(good, companyName, companyType);
		transactionList = new SortedLL<Transaction>();
		total=0;
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
		total+=t.getAmount();
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String toString(){
		String output = "";
		output+=companyName+" ("+typeOfCompany+"): "+isGood;
		return output;
	}

//	@Override
//	public int compareTo(CompanyFE coPany) {
//		if (this.getCompanyName().compareTo(coPany.getCompanyName())<0) {
//			return -1;
//		} else if (this.getCompanyName().compareTo(coPany.getCompanyName())>0){
//			return 1;
//		} else {
//		return 0;
//		}
//	}
}
