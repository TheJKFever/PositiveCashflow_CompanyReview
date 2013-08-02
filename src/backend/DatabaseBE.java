package backend;

import java.io.*;
import au.com.bytecode.opencsv.CSVReader;
import baseClasses.*;
/**
 * 
 * @author Jeroen Goossens & Jon Koehmstedt
 * CSC202 Final Project
 */
public class DatabaseBE implements Serializable {

	private static final long serialVersionUID = -5847593522878274079L;
	private SortedLL<TransactionBE> unknown;
	private SortedLL<TransactionBE> knownTransactions;

	public DatabaseBE(){
		try {
			DatabaseBE newDB = openDatabase();
			this.unknown = newDB.unknown;
			this.knownTransactions = newDB.knownTransactions;
		}
		catch (Exception e){
			this.unknown = new SortedLL<TransactionBE>();
			this.knownTransactions = new SortedLL<TransactionBE>();
		}
	}
	
	public void saveDatabase(DatabaseBE object){
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("files\\Database.dat"));
			out.writeObject(this);
			out.close();
			System.out.println("Save Succeeded");
		} catch (Exception e) {
			System.out.println("Save Failed: "+e);
		}
	}
	
	public DatabaseBE openDatabase(){
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("files\\Database.dat"));
			DatabaseBE newDB = (DatabaseBE)in.readObject();
			in.close();
			return newDB;
		} catch (Exception e) {
			System.out.println("Open Database failed, create new one: "+e);
		}
		return null;
	}
	
	public void addTransaction(TransactionBE t){
		if (!isKnown(t)){
			if (t.getCompany()==null){
				if(!isUnknown(t)){
					unknown.add(t);
				}				
			} else {
				knownTransactions.add(t);
			}
		}		
	}	
	
	
	//TODO TEST THIS
	public void addFromFile(String input){
		CSVReader reader = null;
		String [] nextLine = null;
		int count = 0;
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
//System.out.println(newTransaction);
				addTransaction(newTransaction);
				count++;
			}
System.out.println(count);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isKnown(Transaction t){
		knownTransactions.current = knownTransactions.head;
		while(knownTransactions.getCurrent()!=null){
			if (knownTransactions.current.getData().equals(t)) {
				return true;
			}
			knownTransactions.current=knownTransactions.current.getLink();
		}
System.out.println("false");
System.exit(0);
		return false;
	}
	
	public boolean isUnknown(Transaction t) {
		unknown.current = unknown.head;
		while(unknown.current!=null){
			if (unknown.current.getData().equals(t)) {
				return true;
			}
			unknown.current=unknown.current.getLink();
		}
		return false;
	}
	
	public Company getCurrentCo(){
		return knownTransactions.current.getData().getCompany();
	}
	
	
	//GETTERS
	public SortedLL<TransactionBE> getUnknown() {
		return unknown;
	}

	public SortedLL<TransactionBE> getKnownTransactions() {
		return knownTransactions;
	}
	
	
	public static void main(String[] args){
		DatabaseBE myDB = new DatabaseBE();
		myDB.addFromFile("C:\\Hard Drive\\Education\\NVCC\\Classes\\13\' Summer\\CSC 202\\PositiveCashflow - Company Reviewer\\files\\known transactions.csv");
		myDB.saveDatabase(myDB);
		DatabaseBE testDB = new DatabaseBE();
		System.out.println("__________UNKNOWN TRANSACTIONS___________");
//		System.out.println(testDB.getUnknown().toString());
		System.out.println(testDB.unknown.length());
		
		System.out.println("__________KNOWN TRANSACTIONS___________");
//		System.out.println(testDB.getKnownTransactions().toString());
		System.out.println(testDB.knownTransactions.length());
	}
	
}
