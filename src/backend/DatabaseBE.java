package backend;

import baseClasses.*;

public class DatabaseBE {

	protected SortedLL<TransactionBE> unknown;
	protected SortedLL<TransactionBE> companies;

	public DatabaseBE(){
		unknown=null;
		companies=null;
	}
	
}
