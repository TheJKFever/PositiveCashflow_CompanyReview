package baseClasses;

import backend.TransactionBE;

public class Transaction implements Comparable<Transaction> {
	private String description, date;
	private double amount;
	
	public Transaction(String description){
		super();
		setDescription(description);
	}
	
	public Transaction(String description, double amount, String date) {
		super();
		setDescription(description);
		setAmount(amount);
		setDate(date);
	}
	
	public boolean equals(Transaction t){
		if (t.getDescription().equals(this.getDescription())){
			return true;
		}
		return false;
	}
	
	//GETTERS
	public String getDescription() {
		return description;
	}

	public double getAmount() {
		return amount;
	}

	public String getDate() {
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
	
	//SETTERS
	private void setDescription(String description) {
		this.description = description;
	}

	private void setAmount(double amount) {
		this.amount = amount;
	}

	private void setDate(String date) {
		this.date = date;
	}	
	
	
	public String toString(){
		return ("[" + date + " , " + amount + " , " + description + "]");
	}
	
}
