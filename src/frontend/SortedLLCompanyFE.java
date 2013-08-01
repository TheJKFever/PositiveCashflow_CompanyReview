package frontend;

import baseClasses.SortedLL;

public class SortedLLCompanyFE extends SortedLL<CompanyFE> {
	
	public boolean contains(String coName){
		current=head;
		while (current!=null){
			if (current.getData().getCompanyName().equals(coName)) {
				return true;
			}
			previous=current;
			current=current.getLink();
		}
		return false;
		
	}	

}
