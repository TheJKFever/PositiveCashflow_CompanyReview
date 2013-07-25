package baseClasses;

import java.util.Date;

public class Transaction {
	private String description;
	private double amount;
	private Date date;
	
	public Transaction(String description){
		super();
		this.description = description;
	}
	
	public Transaction(String description, double amount, Date date) {
		super();
		this.description = description;
		this.amount = amount;
		this.date = date;
	}
	
	//GETTERS
	public String getDescription() {
		return description;
	}

	public double getAmount() {
		return amount;
	}

	public Date getDate() {
		return date;
	}
	
	//SETTERS
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public void setAmount(double amount) {
//		this.amount = amount;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}	
	
}
