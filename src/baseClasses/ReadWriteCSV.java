package baseClasses;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

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
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     // feed in your array (or convert your data to an array)
	     String[] entries = "first#second#third".split("#");
	     writer.writeNext(entries);
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Transaction> readTransactions(String input){
		CSVReader reader = null;
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		try {
			reader = new CSVReader(new FileReader(input));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String [] nextLine = null;
		//get correct index
		int indexDate = -1;
		int indexAmount = -1;
		int indexDescription = -1;
		try {
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
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			while ((nextLine = reader.readNext()) != null) {
				// nextLine[] is an array of values from the line
				transactions.add(new Transaction(nextLine[indexDate], Double.valueOf(nextLine[indexAmount]), nextLine[indexDescription]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return transactions;
	}

}