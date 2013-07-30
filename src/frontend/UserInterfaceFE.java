package frontend;

import java.io.File;
import baseClasses.*;

public class UserInterfaceFE {

	protected SortedLL<CompanyFE> companies;
	private double total=0, totalGood, totalBad, totalUnknown;
	private File input;
	
	
	public UserInterfaceFE() {
		this.companies = new SortedLL<CompanyFE>();
		this.total = 0;
		this.totalGood = 0;
		this.totalBad = 0;
		this.totalUnknown = 0;
		this.input = null;
	}
	
	public UserInterfaceFE(File input){
		super();
		this.input = input;
	}

	//GETTERS
	public SortedLL<CompanyFE> getCompanies() {
		return companies;
	}

	public double getTotal() {
		return total;
	}

	public double getTotalGood() {
		return totalGood;
	}

	public double getTotalBad() {
		return totalBad;
	}

	public double getTotalUnknown() {
		return totalUnknown;
	}

	public double getPercentGood() {
		return totalGood/total;
	}

	public double getPercentBad() {
		return totalBad/total;
	}

	public double getPercentUnknown() {
		return totalUnknown/total;
	}

	public File getInput() {
		return input;
	}
	
	public Object[][] getGoodTransactions() {
		// Loop through companies and return Object[][]: {"Date", "Company", "Transaction Description", "Amount"}		
		return null;
	}
	
	public Object[][] getBadTransactions() {
		// Loop through companies and return Object[][]: {"Date", "Company", "Transaction Description", "Amount"}		
		return null;
	}

	public Object[][] getUnknownTransactions() {
		// Loop through companies and return Object[][]: {"Date", "Transaction Description", "Amount", "Company", "Good/Bad"}		
		return null;
	}


	//SETTERS
	public void setCompanies(SortedLL<CompanyFE> companies) {
		this.companies = companies;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setTotalGood(double totalGood) {
		this.totalGood = totalGood;
	}

	public void setTotalBad(double totalBad) {
		this.totalBad = totalBad;
	}

	public void setTotalUnknown(double totalUnknown) {
		this.totalUnknown = totalUnknown;
	}

	public void setInput(File input) {
		this.input = input;
	}

}
