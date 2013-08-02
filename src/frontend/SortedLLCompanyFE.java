package frontend;

import baseClasses.SortedLL;

public class SortedLLCompanyFE extends SortedLL<CompanyFE> {

	private static final long serialVersionUID = -3622776240792779301L;

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
