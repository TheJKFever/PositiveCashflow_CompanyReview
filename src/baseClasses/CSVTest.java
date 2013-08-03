package baseClasses;

import java.io.IOException;
/**
 * 
 * @author Jeroen Goossens & Jon Koehmstedt
 * CSC202 Final Project
 */
public class CSVTest {

private static final String ADDRESS_FILE="addresses.csv";
	
	public static void main(String[] args) throws IOException {
	
		ReadWriteCSV temp = new ReadWriteCSV();
		
		try {
			for(Transaction t : temp.readTransactions(ADDRESS_FILE)){
				System.out.println(t.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Transaction temp2 = new Transaction("blkur", 898.34, "tonight");
		System.out.println(temp2);

	}
}
