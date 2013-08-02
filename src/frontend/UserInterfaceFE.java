package frontend;

import java.io.File;
import java.util.ArrayList;
import backend.DatabaseBE;
import backend.TransactionBE;
import baseClasses.*;
/**
 * 
 * @author Jeroen Goossens & Jon Koehmstedt
 * CSC202 Final Project
 */
public class UserInterfaceFE {

	protected SortedLL<CompanyFE> companies;
	private double total=0, totalGood, totalBad, totalUnknown;
	private File input;
	ReadWriteCSV readWrite;
	private CompanyFE unknown;
	private boolean found;


	public UserInterfaceFE() {
		this.companies = new SortedLL<CompanyFE>();
		unknown = new CompanyFE(true, "Unknown", "");
		companies.add(unknown);
		this.total = 0;
		this.totalGood = 0;
		this.totalBad = 0;
		this.totalUnknown = 0;
		this.input = null;
	}

	public UserInterfaceFE(String input, DatabaseBE tempDB){
		super();
		this.companies = new SortedLL<CompanyFE>();
		unknown = new CompanyFE(true, "Unknown", "");
		companies.add(unknown);
		this.input = new File(input);
		readData(this.input);
		updateUnknown(tempDB);
	}

	public void readData(File input2) { //Adds all transactions to unknown
		readWrite = new ReadWriteCSV();
		try { //Creates unknown company in companies, and adds all transactions from file to it
			ArrayList<Transaction> tempList = readWrite.readTransactions(input2.getCanonicalPath());
//System.out.println(companies.get(unknown).getTypeOfCompany());
			for (Transaction i: tempList){
				unknown.addTransaction(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	private void updateUnknown(DatabaseBE tempDB){
		//see if is known, if is then search for company in companies, 
		//if find, add transaction, if not add company and add transaction
		//if not known, add to backend and leave in unknown
		unknown.getTransactionList().current = unknown.getTransactionList().head;
		while (unknown.getTransactionList().current!=null){
			found=false;
			//If transaction is in BE known list
			if (tempDB.isKnown(unknown.getTransactionList().getCurrent().getData())){
System.out.println("Transaction Is Known: "+unknown.getTransactionList().getCurrent().getData());
				companies.current=companies.head;
				//iterate through FE companies to see if it'a already in the list
				while (companies.current!=null){
					//If find company in FE list
					if (companies.getCurrent().getData().getCompanyName().equals(tempDB.getCurrentCo().getCompanyName())){
//System.out.println("Company is already in list");
						found=true;
						//Did not find transaction
						//add current transaction from unknown to known company					
						companies.getCurrent().getData().addTransaction(unknown.getTransactionList().getCurrent().getData());
						//add total to company
						double tempAmount = unknown.getTransactionList().getCurrent().getData().getAmount();
						companies.getCurrent().getData().setTotal(companies.getCurrent().getData().getTotal()+tempAmount);
						unknown.getTransactionList().remove();
						break;
					}
					companies.current = companies.current.getLink();
				}
				if (!found){
					//Did not find company in FE, add to companies
					CompanyFE newCompanyFE = new CompanyFE(tempDB.getCurrentCo().isGood(), tempDB.getCurrentCo().getCompanyName(), tempDB.getCurrentCo().getTypeOfCompany());
					newCompanyFE.addTransaction(unknown.getTransactionList().getCurrent().getData());
					unknown.getTransactionList().remove();
					companies.add(newCompanyFE);
				}
			} 
			else { //Could not find in known companies, whatever is left over add to BEunknown
System.out.println("Transaction Is Unknown: "+unknown.getTransactionList().getCurrent().getData());
				if (!tempDB.isUnknown(unknown.getTransactionList().getCurrent().getData())){
System.out.println("Is in the unknown backend test");
					TransactionBE newTBE = new TransactionBE(unknown.getTransactionList().getCurrent().getData().getDescription(),null);
					tempDB.getUnknown().add(newTBE);
				}						
			}
System.out.print(unknown.getTransactionList().length()+": ");
//if (unknown.getTransactionList().length()==1569) break;
			unknown.getTransactionList().previous = unknown.getTransactionList().current;
			unknown.getTransactionList().current = unknown.getTransactionList().current.getLink();
		}
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


	public Object[][] getGoodTransactions() {
		//TODO
		// Loop through companies and return Object[][]: {"Date", "Company", "Transaction Description", "Amount"}		
		return null;
	}

	public Object[][] getBadTransactions() {
		//TODO
		// Loop through companies and return Object[][]: {"Date", "Company", "Transaction Description", "Amount"}		
		return null;
	}

	public Object[][] getUnknownTransactions() {
		//TODO
		// Loop through companies and return Object[][]: {"Date", "Transaction Description", "Amount", "Company", "Good/Bad"}		
		return null;
	}


	//SETTERS
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

	//TODO Test the clean method and others
	public static void main(String[] args){
		DatabaseBE myDB = new DatabaseBE();
		
		
		UserInterfaceFE UI = new UserInterfaceFE("files\\Original transactions.csv",myDB);
		System.out.println("Completely finished");
//		CompanyFE unknowntester = new CompanyFE(true,"Unknown","");
		System.out.print(UI.unknown.getTransactionList().toString());
//		System.out.println(UI.companies.getCurrent().getTransactionList().toString());
	}
}
