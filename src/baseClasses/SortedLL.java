package baseClasses;
/**
 * 
 * @author Jeroen Goossens & Jon Koehmstedt
 * CSC202 Final Project
 */

import java.util.Iterator;

public class SortedLL<T> implements SortedLLInterface<T>, Iterable<T> {
	
	protected LLNode<T> head,previous,current;
	protected int length = 0;
	
	public SortedLL() {
		head=null;
		previous=null;
		current=null;
	}

	public void remove(){
		if (current==head) 		//head node (includes single node case)
			head.setLink(head.getLink());
		else { 					//other than head nodes
			previous.setLink(current.getLink());
		}
		length-=1;
	}
	
	@Override
	public void remove(T element) {
		if (contains(element)) {	//If doesn't contain element, skips remove
			if (current==head) 		//head node (includes single node case)
				head.setLink(head.getLink());
			else { 					//other than head nodes
				previous.setLink(current.getLink());
			}
			length-=1;
		}
	}
	
	@Override
	public T get(T element){
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
	public void add(T element) {	// sorted insert
		LLNode<T> newNode = new LLNode<T>(element);
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
	public boolean contains(T element) {
		current=head;
		while (current!=null){
			if (current.getData().equals(element)) {
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

//TODO Create iterator
	public Iterator<T> iterator() {
		return null;
//		return this.get(m_ActiveProfile);
	}
	

		
}


