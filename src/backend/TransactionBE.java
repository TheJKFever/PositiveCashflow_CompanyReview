package backend;

import baseClasses.*;

public class TransactionBE extends Transaction {
	
	private Company company;
	
	public TransactionBE(){
		super(null);
		company=null;
	}
	
	public TransactionBE(String description) {
		super(description);
		this.company=null;
	}
	
	public boolean equals(TransactionBE t){
		if (t.getDescription().equals(this.getDescription())){
			return true;
		}
		return false;
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
