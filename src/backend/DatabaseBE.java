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

	public void saveDatabase(DatabaseBE object){
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Database.dat"));
			out.writeObject(this);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DatabaseBE openDatabase(){
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("Database.dat"));
			DatabaseBE newDB = (DatabaseBE) in.readObject();
			in.close();
			return newDB;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	//GETTERS
	public SortedLL<TransactionBE> getUnknown() {
		return unknown;
	}

	public SortedLL<TransactionBE> getCompanies() {
		return companies;
	}

	
}
