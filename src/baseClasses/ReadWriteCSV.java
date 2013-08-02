package baseClasses;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
/**
 * 
 * @author Jeroen Goossens & Jon Koehmstedt
 * CSC202 Final Project
 */
public class ReadWriteCSV {

	public void read(String input){
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(input));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String [] nextLine = null;
		try {
			while ((nextLine = reader.readNext()) != null) {
				// nextLine[] is an array of values from the line
				System.out.println(nextLine[0] + nextLine[1] + "etc...");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String[] output, String adress){
		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter("yourfile.csv"), '\t');
			String[] entries = "first#second#third".split("#");
		     writer.writeNext(entries);
				writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Transaction> readTransactions(String input) throws Exception{
		CSVReader reader = null;
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		String [] nextLine = null;
		//get correct index
		int indexDate = -1;
		int indexAmount = -1;
		int indexDescription = -1;
		try {
			reader = new CSVReader(new FileReader(input));
			if((nextLine = reader.readNext()) != null){
				int length = nextLine.length;
				for(int i = 0; i < length; i++){
					if(nextLine[i].equalsIgnoreCase("date")){
						indexDate = i;
					}
					if(nextLine[i].equalsIgnoreCase("amount")){
						indexAmount = i;
					}
					if(nextLine[i].equalsIgnoreCase("original description")){
						indexDescription = i;
					}
				}
			}
			if(indexDate==-1 || indexAmount == -1 || indexDescription == -1){
				reader.close();
				throw new Exception("Formatted incorrectly");
			}
			reader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			while ((nextLine = reader.readNext()) != null) {
				// nextLine[] is an array of values from the line
				System.out.println(nextLine[indexDate]);
				transactions.add(new Transaction(nextLine[indexDescription], Double.valueOf(nextLine[indexAmount]), nextLine[indexDate]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return transactions;
	}

}