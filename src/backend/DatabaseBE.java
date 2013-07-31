package backend;

import java.io.*;

import baseClasses.*;

public class DatabaseBE implements Serializable {

	private static final long serialVersionUID = 1L;
	protected SortedLL<TransactionBE> unknown;
	protected SortedLL<TransactionBE> companies;

	public DatabaseBE(){
		unknown = new SortedLL<TransactionBE>();
		companies = new SortedLL<TransactionBE>();
	}
	
	public DatabaseBE(String fileName){
		openDatabase(fileName);
	}

	public void saveDatabase(DatabaseBE object){
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Database.dat"));
			out.writeObject(this);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DatabaseBE openDatabase(String fileName){
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
			DatabaseBE newDB = (DatabaseBE) in.readObject();
			in.close();
			return newDB;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isKnown(TransactionBE t){
		for (int i=0; i<companies.length();i++){
			companies.current = companies.head;
			while(companies.current!=null){
				if (companies.current.getData().equals(t)) {
					return true;
				}
				companies.previous=companies.current;
				companies.current=companies.current.getLink();
			}
		}
		return false;
	}
	
	public boolean isUnknown(TransactionBE t) {
		for (int i=0; i<unknown.length();i++){
			unknown.current = unknown.head;
			while(unknown.current!=null){
				if (unknown.current.getData().equals(t)) {
					return true;
				}
				unknown.previous=unknown.current;
				unknown.current=unknown.current.getLink();
			}
		}
		return false;
	}
	
	public void addFromFile(String input){
		//TODO
	}
		
	public void addTransaction(TransactionBE t){
		//TODO
		if (!isKnown(t)){
			if (t.getCompany()==null){
				if(!isUnknown(t)){
					unknown.add(t);
				}				
			} else {
				companies.add(t);
			}
		}		
	}
	
	
	//GETTERS
	public SortedLL<TransactionBE> getUnknown() {
		return unknown;
	}

	public SortedLL<TransactionBE> getCompanies() {
		return companies;
	}

	
}
