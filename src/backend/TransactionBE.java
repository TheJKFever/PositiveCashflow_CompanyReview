package backend;

import baseClasses.*;

public class TransactionBE extends Transaction {
	
	private Company company;
	
	public TransactionBE(){
		super(null);
		company=null;
	}
	
	public TransactionBE(String description, Company company) {
		super(description);
		this.company=company;
	}
	
	//GETTERS
	public Company getCompany() {
		return company;
	}
	
	//SETTERS
	public void setCompany(Company company) {
		this.company = company;
	}
}
