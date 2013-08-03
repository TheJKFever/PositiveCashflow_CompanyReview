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
	private double total, totalGood, totalBad, totalUnknown;
	private int countGood, countBad, countUnknown;
	private File input;
	ReadWriteCSV readWrite;
	private CompanyFE unknown;
	private boolean found;


	public UserInterfaceFE() {
		this.companies = new SortedLL<CompanyFE>();
		unknown = new CompanyFE((Boolean)null, "Unknown", "");
		companies.add(unknown);
		this.total = 0;
		this.totalGood = 0;
		this.totalBad = 0;
		this.totalUnknown = 0;
		this.countGood=0;
		this.countBad=0;
		this.countUnknown=0;
		this.input = null;
	}

	public UserInterfaceFE(String input, DatabaseBE tempDB){
		super();
		this.companies = new SortedLL<CompanyFE>();
		unknown = new CompanyFE(true, "Unknown", "");
		companies.add(unknown);
		this.total = 0;
		this.totalGood = 0;
		this.totalBad = 0;
		this.totalUnknown = 0;
		this.countGood=0;
		this.countBad=0;
		this.countUnknown=0;
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
//System.out.println("Transaction Is Known: "+unknown.getTransactionList().getCurrent().getData());
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
						distributeAmount(unknown.getTransactionList().getCurrent().getData(), companies.getCurrent().getData());
						//add total to company
						unknown.getTransactionList().remove();
						break;
					}
					companies.current = companies.current.getLink();
				}
				if (!found){
//System.out.println("Company was not in list");
					//Did not find company in FE, add to companies
					CompanyFE newCompanyFE = new CompanyFE(tempDB.getCurrentCo().isGood(), tempDB.getCurrentCo().getCompanyName(), tempDB.getCurrentCo().getTypeOfCompany());
					newCompanyFE.addTransaction(unknown.getTransactionList().getCurrent().getData());
					distributeAmount(unknown.getTransactionList().getCurrent().getData(), companies.getCurrent().getData());
					unknown.getTransactionList().remove();
					companies.add(newCompanyFE);
				}
			} 
			else { //Could not find in known companies, whatever is left over add to BEunknown
//System.out.println("Transaction Is Unknown: "+unknown.getTransactionList().getCurrent().getData());
				distributeAmount(unknown.getTransactionList().getCurrent().getData(), unknown);
				if (!tempDB.isUnknown(unknown.getTransactionList().getCurrent().getData())){
					TransactionBE newTBE = new TransactionBE(unknown.getTransactionList().getCurrent().getData().getDescription(),null);
					tempDB.getUnknown().add(newTBE);
				}						
			}
//System.out.print(unknown.getTransactionList().length()+": ");
			unknown.getTransactionList().previous = unknown.getTransactionList().current;
			unknown.getTransactionList().current = unknown.getTransactionList().current.getLink();
		}
	}

	
	private void distributeAmount(Transaction t, CompanyFE c) {
		if (c.isGood()==(Boolean)null){
			totalUnknown+=t.getAmount();
			countUnknown++;
		} else if (c.isGood()) {
			totalGood+=t.getAmount();
			countGood++;
		} else {
			totalBad+=t.getAmount();
			countBad++;
		}
		total+=t.getAmount();
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


	public String[][] getGoodTransactions() {
		String[][] object = new String[countGood][4];
		companies.current = companies.getHead();
		int count=0;
		while (companies.current!=null){
			if (companies.current.getData().isGood()){
				companies.current.getData().getTransactionList().current = companies.current.getData().getTransactionList().getHead();
				while(companies.current.getData().getTransactionList().current!=null){
					object[count][0] = companies.current.getData().getTransactionList().current.getData().getDate().toString();
					object[count][1] = companies.current.getData().getCompanyName();
					object[count][2] = companies.current.getData().getTransactionList().current.getData().getDescription();
					object[count][3] = ""+companies.current.getData().getTransactionList().current.getData().getAmount();
					count++;
					companies.current.getData().getTransactionList().previous = companies.current.getData().getTransactionList().current;
					companies.current.getData().getTransactionList().current = companies.current.getData().getTransactionList().current.getLink();
				}
			}
			companies.previous = companies.getCurrent();
			companies.current = companies.getCurrent().getLink();
		}
		return object;
	}

	public String[][] getBadTransactions() {
		String[][] object = new String[countBad][4];
		companies.current = companies.getHead();
		int count=0;
		while (companies.current!=null){
			if (!companies.current.getData().isGood()){
				companies.current.getData().getTransactionList().current = companies.current.getData().getTransactionList().getHead();
				while(companies.current.getData().getTransactionList().current!=null){
					object[count][0] = companies.current.getData().getTransactionList().current.getData().getDate().toString();
					object[count][1] = companies.current.getData().getCompanyName();
					object[count][2] = companies.current.getData().getTransactionList().current.getData().getDescription();
					object[count][3] = ""+companies.current.getData().getTransactionList().current.getData().getAmount();
					count++;
					companies.current.getData().getTransactionList().previous = companies.current.getData().getTransactionList().current;
					companies.current.getData().getTransactionList().current = companies.current.getData().getTransactionList().current.getLink();
				}
			}
			companies.previous = companies.getCurrent();
			companies.current = companies.getCurrent().getLink();
		}
		return object;
	}
	
	public String[][] getUnknownTransactions() {
		String[][] object = new String[countBad][5];
		companies.current = companies.getHead();
		int count=0;
		while (companies.current!=null){
			if (companies.current.getData().isGood()==(Boolean)null){
				companies.current.getData().getTransactionList().current = companies.current.getData().getTransactionList().getHead();
				while(companies.current.getData().getTransactionList().current!=null){
					object[count][0] = companies.current.getData().getTransactionList().current.getData().getDate().toString();
					object[count][1] = companies.current.getData().getTransactionList().current.getData().getDescription();
					object[count][2] = ""+companies.current.getData().getTransactionList().current.getData().getAmount();
					object[count][3] = "";
					object[count][4] = "";					
					count++;
					companies.current.getData().getTransactionList().previous = companies.current.getData().getTransactionList().current;
					companies.current.getData().getTransactionList().current = companies.current.getData().getTransactionList().current.getLink();
				}
			}
			companies.previous = companies.getCurrent();
			companies.current = companies.getCurrent().getLink();
		}
		return object;
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
//		System.out.print(UI.unknown.getTransactionList().toString());
//		System.out.println(UI.companies.getCurrent().getTransactionList().toString());
	}
}
