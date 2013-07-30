package backend;

import baseClasses.*;

public class DatabaseBE {

	protected SortedLL<TransactionBE> unknown;
	protected SortedLL<TransactionBE> companies;

	public DatabaseBE(){
		unknown = new SortedLL<TransactionBE>();
		companies = new SortedLL<TransactionBE>();
	}

	//GETTERS
	public SortedLL<TransactionBE> getUnknown() {
		return unknown;
	}

	public SortedLL<TransactionBE> getCompanies() {
		return companies;
	}

	public void writeToFile(SortedLL<TransactionBE> list){
		//TODO
	}
	
	public SortedLL<TransactionBE> readToFile(){
		//TODO
		return null;
	}
	
	
	
	
}
