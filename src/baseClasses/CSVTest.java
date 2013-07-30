package baseClasses;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class CSVTest {

private static final String ADDRESS_FILE="transactions.csv";
	
	public static void main(String[] args) throws IOException {
		
		CSVReader reader = new CSVReader(new FileReader(ADDRESS_FILE));
		String [] nextLine;
		
//		System.out.println("first line consists of " + reader.readNext()[0] + " and "  +  reader.readNext()[1] + " and "  +reader.readNext()[2]);
		
		while ((nextLine = reader.readNext()) != null) {
			System.out.println(nextLine[0] + " \t|| " + nextLine[1] + " \t\t\t\t|| " + nextLine[2] );
		}
		
		// Try writing it back out as CSV to the console
//		CSVReader reader2 = new CSVReader(new FileReader(ADDRESS_FILE));
//		List<String[]> allElements = reader2.readAll();
//		StringWriter sw = new StringWriter();
//		CSVWriter writer = new CSVWriter(sw);
//		writer.writeAll(allElements);
//		
//		System.out.println("\n\nGenerated CSV File:\n\n");
//		System.out.println(sw.toString());
//		
		
	}
}
