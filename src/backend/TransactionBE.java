package backend;

import java.io.Serializable;

import baseClasses.*;
/**
 * 
 * @author Jeroen Goossens & Jon Koehmstedt
 * CSC202 Final Project
 */
public class TransactionBE extends Transaction implements Serializable {
	
	private static final long serialVersionUID = -8665707701617784839L;
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
	
	@Override
	public int compareTo(Transaction other) {
		if (this.getDescription().compareTo(other.getDescription())<0) {
			return -1;
		} else if (this.getDescription().compareTo(other.getDescription())>0){
			return 1;
		} else { //Dates are the same
			if (this.getDescription().compareTo(other.getDescription())<0){
				return -1;
			} else if (this.getDescription().compareTo(other.getDescription())>0){
				return 1;
			} else {return 0;} //Dates and Description are the same
		}
	}
	
	public String toString(){
		return ("[" + this.getDescription() + " , " + company.getCompanyName() + " , " + company.getTypeOfCompany() + " , " + company.isGood() + "]");
	}
	
}
