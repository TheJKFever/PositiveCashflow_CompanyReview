package baseClasses;

public class SortedLL<T> implements SortedLLInterface<T>{
	
	LLNode<T> head,previous,current;
	
	public SortedLL() {
		head=null;
		previous=null;
		current=null;
	}

	@Override
	public void remove(T element) {
		if (contains(element)) {	//If doesn't contain element, skips remove
			if (current==head) 		//head node (includes single node case)
				head.setLink(head.getLink());
			else { 					//other than head nodes
				previous.setLink(current.getLink());
			}
		}
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
	}

	@Override
	public void clear() {
		head=null;
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
	
	public T getHead() {
		return head.getData();
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
	
}
