package backend;

import java.io.*;
import au.com.bytecode.opencsv.CSVReader;
import baseClasses.*;

public class DatabaseBE implements Serializable {

	private static final long serialVersionUID = 1L;
	protected SortedLL<TransactionBE> unknown;
	protected SortedLL<TransactionBE> companies;

	public DatabaseBE(){
		unknown = new SortedLL<TransactionBE>();
		companies = new SortedLL<TransactionBE>();
	}
	
	
	//TODO Make sure this returns the proper value and doesn't just do nothing
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
	
	public void addFromFile(String input){
		//TODO input is a file, read CSV
		CSVReader reader = null;
		String [] nextLine = null;
		int indexCompany = -1;
		int indexType = -1;
		int indexDescription = -1;
		int indexGood = -1;
		try {
			reader = new CSVReader(new FileReader(input));
			if((nextLine = reader.readNext()) != null){
				int length = nextLine.length;
				for(int i = 0; i < length; i++){
					if(nextLine[i].equalsIgnoreCase("company")){
						indexCompany = i;
					}
					if(nextLine[i].equalsIgnoreCase("type of company")){
						indexType = i;
					}
					if(nextLine[i].equalsIgnoreCase("good/bad")){
						indexGood = i;
					}
					if(nextLine[i].equalsIgnoreCase("original description")){
						indexDescription = i;
					}
				}
			}
			if(indexCompany==-1 || indexType == -1 || indexDescription == -1 || indexGood ==-1){
				new Exception("Formatted incorrectly");
			}
			while ((nextLine = reader.readNext()) != null) {
				// nextLine[] is an array of values from the line
				boolean tempGood;
				if (nextLine[indexGood].equalsIgnoreCase("Good")){
					tempGood = true;
				} else {
					tempGood = false;
				}
				TransactionBE newTransaction = new TransactionBE(nextLine[indexDescription], new Company(tempGood, nextLine[indexCompany], nextLine[indexType]));
				addTransaction(newTransaction);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addTransaction(TransactionBE t){
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

	
	//PRIVATE METHODS
	private boolean isKnown(TransactionBE t){
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
	
	private boolean isUnknown(TransactionBE t) {
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
	
	
	
}
