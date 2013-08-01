package frontend;

import baseClasses.ElementDoesNotExistException;
import baseClasses.LLNode;
import baseClasses.SortedLL;

public class SortedLLCompanyFE extends SortedLL<CompanyFE> {

	public SortedLLCompanyFE() {
		super();
	}
	
	@Override
	public CompanyFE get(CompanyFE element){
		try {
			if (!contains(element)){
				throw new ElementDoesNotExistException("Element not found in list");
			}
		} catch (ElementDoesNotExistException e){
			System.out.println(e.getMessage());	
		}
		return current.getData();		
	}
	
	@Override
	public void add(CompanyFE element) {	// sorted insert
		LLNode<CompanyFE> newNode = new LLNode<CompanyFE>(element);
		current = head;
		while (current!=null){
			if (current.compareTo(newNode)>0){
				if (current==head){					//add to front
					newNode.setLink(head);
					head=newNode;
					break;
				} else {							//add in middle
					previous.setLink(newNode);
					newNode.setLink(current);
					break;
				}
			} else if (current.getLink()==null) {	//add to end
					current.setLink(newNode);
					break;
			}
			previous=current;
			current=current.getLink();
		}
		if (head==null){ 			//list is empty
			head = newNode;
		}
		length+=1;
	}

	@Override
	public void clear() {
		head=null;
		length=0;
	}
	
	@Override
	public boolean contains(CompanyFE element) {
		current=head;
		while (current!=null){
			if (current.getData().getCompanyName().equals(element.getCompanyName())) {
				return true;
			}
			previous=current;
			current=current.getLink();
		}
		return false;
	}
	
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
		
	public String toString() {
		String list = "";
		current = head;
		while(current!=null) {
			list += current.getData() + "\n";
			current = current.getLink();
		}
		return list;
	}
	
	//GETTERS
	public int length(){
		return length;
	}

	public T getHead() {
		return head.getData();
	}	

	public T getPrevious() {
		return previous.getData();
	}

	public T getCurrent() {
		return current.getData();
	}

	//SETTERS
	public void setHead(LLNode<T> head) {
		this.head = head;
	}

	public void setPrevious(LLNode<T> previous) {
		this.previous = previous;
	}

	public void setCurrent(LLNode<T> current) {
		this.current = current;
	}
	

	

}
