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
		unknown = new CompanyFE(0, "Unknown", "");
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
		unknown = new CompanyFE(0, "Unknown", "");
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
					distributeAmount(unknown.getTransactionList().getCurrent().getData(), newCompanyFE);
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
		if (c.isGood()==0){
			totalUnknown+=t.getAmount();
			countUnknown++;
		} else if (c.isGood()==1) {
			totalGood+=t.getAmount();
			countGood++;
		} else if (c.isGood()==2){
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
		return ((int)(total*100))/100.0;
	}

	public double getTotalGood() {
		return ((int)(totalGood*100))/100.0;
	}

	public double getTotalBad() {
		return ((int)(totalBad*100))/100.0;
	}

	public double getTotalUnknown() {
		return ((int)(totalUnknown*100))/100.0;
	}

	public double getPercentGood() {
		return ((int)(totalGood/total*10000))/100.0;
	}

	public double getPercentBad() {
		return ((int)(totalBad/total*10000))/100.0;
	}

	public double getPercentUnknown() {
		return ((int)(totalUnknown/total*10000))/100.0;
	}


	public String[][] getGoodTransactions() {
		String[][] object = new String[countGood+1][4];
		companies.current = companies.getHead();
		int count=0;
		while (companies.current!=null){
			if (companies.current.getData().isGood()==1){
				companies.current.getData().getTransactionList().current = companies.current.getData().getTransactionList().getHead();
				while(companies.current.getData().getTransactionList().current!=null){
					object[count][0] = companies.current.getData().getTransactionList().current.getData().getDate().toString();
					object[count][1] = companies.current.getData().getCompanyName();
					object[count][2] = companies.current.getData().getTransactionList().current.getData().getDescription();
					object[count][3] = ""+companies.current.getData().getTransactionList().current.getData().getAmount();
					count++;
					companies.current.getData().getTransactionList().previous = companies.current.getData().getTransactionList().getCurrent();
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
System.out.println(countBad);
		companies.current = companies.getHead();
		int count=0;
		while (companies.current!=null){
			if (companies.current.getData().isGood()==2){
				companies.current.getData().getTransactionList().current = companies.current.getData().getTransactionList().getHead();
				while(companies.current.getData().getTransactionList().current!=null){
					object[count][0] = companies.current.getData().getTransactionList().current.getData().getDate().toString();
					object[count][1] = companies.current.getData().getCompanyName();
					object[count][2] = companies.current.getData().getTransactionList().current.getData().getDescription();
					object[count][3] = ""+companies.current.getData().getTransactionList().current.getData().getAmount();
					count++;
					companies.current.getData().getTransactionList().previous = companies.current.getData().getTransactionList().getCurrent();
					companies.current.getData().getTransactionList().current = companies.current.getData().getTransactionList().current.getLink();
				}
			}
			companies.previous = companies.getCurrent();
			companies.current = companies.getCurrent().getLink();
		}
		return object;
	}
	
	public String[][] getUnknownTransactions() {
		String[][] object = new String[countUnknown][5];
System.out.println(countUnknown);
		companies.current = companies.getHead();
		int count=0;
		while (companies.current!=null){
			if (companies.current.getData().isGood()==0){
				companies.current.getData().getTransactionList().current = companies.current.getData().getTransactionList().getHead();
				while(companies.current.getData().getTransactionList().current!=null){
System.out.println(companies.current.getData().getTransactionList().getCurrent().getData().getDate()+"\t"+companies.current.getData().getTransactionList().getCurrent().getData().getDescription()+"\t"+companies.current.getData().getTransactionList().getCurrent().getData().getAmount());
					object[count][0] = companies.current.getData().getTransactionList().getCurrent().getData().getDate().toString();
					object[count][1] = companies.current.getData().getTransactionList().getCurrent().getData().getDescription();
					object[count][2] = ""+companies.current.getData().getTransactionList().getCurrent().getData().getAmount();
					object[count][3] = "";
					object[count][4] = "";					
					count++;
					companies.current.getData().getTransactionList().previous = companies.current.getData().getTransactionList().getCurrent();
					companies.current.getData().getTransactionList().current = companies.current.getData().getTransactionList().current.getLink();
				}
			}
			companies.previous = companies.getCurrent();
			companies.current = companies.getCurrent().getLink();
		}
		return object;
	}
	
	public String[][] getCompanySummary() {
		String[][] object = new String[companies.length()][3];
		companies.current = companies.getHead();
		int count=0;
		companies.current = companies.getHead();
		while(companies.current!=null){
			object[count][0] = companies.current.getData().getCompanyName();
			object[count][1] = getGoodString(companies.current.getData());
			object[count][2] = ""+companies.current.getData().getTotal();
			count++;
			companies.previous = companies.getCurrent();
			companies.current = companies.getCurrent().getLink();
		}
		return object;
	}

	public String getGoodString(Company c){
		if (c.isGood()==1) {
			return "Good";
		} else if (c.isGood()==2){
			return "Bad";
		}
		return "Unknown";
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


	public static void main(String[] args){
		DatabaseBE myDB = new DatabaseBE();
		UserInterfaceFE UI = new UserInterfaceFE("files\\Original transactions.csv",myDB);
		
//		System.out.println(UI.unknown.getTransactionList());
		
		
//		UI.companies.current = UI.companies.getHead();
//		UI.companies.current = UI.companies.getHead();
//		while(UI.companies.current!=null){
//			System.out.print(UI.companies.current.getData().getCompanyName()+"\t"+UI.getGoodString(UI.companies.current.getData())+"\t"+UI.companies.current.getData().getTotal()+"\n");
//			UI.companies.previous = UI.companies.getCurrent();
//			UI.companies.current = UI.companies.getCurrent().getLink();
//		}
		
		
		
//		UI.companies.current = UI.companies.getHead();
//		while (UI.companies.current!=null){
//			if (UI.companies.current.getData().isGood()==0){
//				UI.companies.current.getData().getTransactionList().current = UI.companies.current.getData().getTransactionList().getHead();
//				while(UI.companies.current.getData().getTransactionList().current!=null){
//					System.out.println(UI.companies.current.getData().getTransactionList().current.getData().getDate().toString()+"\t"
//							+ UI.companies.current.getData().getTransactionList().current.getData().getDescription()+"\t"
//							+ UI.companies.current.getData().getTransactionList().current.getData().getAmount());
//					UI.companies.current.getData().getTransactionList().previous = UI.companies.current.getData().getTransactionList().current;
//					UI.companies.current.getData().getTransactionList().current = UI.companies.current.getData().getTransactionList().current.getLink();
//				}
//			}
//			UI.companies.previous = UI.companies.getCurrent();
//			UI.companies.current = UI.companies.getCurrent().getLink();
//		}
		
		
		
//		System.out.println("Completely finished");
//		System.out.println("$"+UI.getTotal());
//		System.out.println("$"+UI.getTotalBad());
//		System.out.println("$"+UI.getTotalGood());
//		System.out.println("$"+UI.getTotalUnknown());
//		System.out.println(UI.getPercentBad()+"%");

	}

}
