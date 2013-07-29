package baseClasses;

import java.util.Date;

public class Transaction implements Comparable<Transaction> {
	private String description;
	private double amount;
	private Date date;
	
	public Transaction(String description){
		super();
		setDescription(description);
	}
	
	public Transaction(String description, double amount, Date date) {
		super();
		setDescription(description);
		setAmount(amount);
		setDate(date);
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

	@Override
	public int compareTo(Transaction other) {
		if (this.getDate().compareTo(other.getDate())<0) {
			return -1;
		} else if (this.getDate().compareTo(other.getDate())>0){
			return 1;
		} else { //Dates are the same
			if (this.getDescription().compareTo(other.getDescription())<0){
				return -1;
			} else if (this.getDescription().compareTo(other.getDescription())>0){
				return 1;
			} else {return 0;} //Dates and Description are the same
		}
	}
	
<<<<<<< HEAD
	//SETTERS - Dont need setters, just create a new transaction
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
=======
	//SETTERS
	private void setDescription(String description) {
		this.description = description;
	}

	private void setAmount(double amount) {
		this.amount = amount;
	}

	private void setDate(Date date) {
		this.date = date;
	}	
>>>>>>> b2e8a61a79d8e2ad87acf33e361bcef138b83fd0
	
}
