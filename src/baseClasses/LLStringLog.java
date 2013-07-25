package baseClasses;


public class LLStringLog<T> implements StringLogInterface{
	
	LLNode<T> head,previous,current;
	
	public LLStringLog() {
		head=null;
		previous=null;
		current=null;
	}
	@Override
	public void insert(String element) {
		// insert beginning	
		LLNode<T> newNode = new LLNode(element);
		previous = head;
		head = newNode;	

		if(head!=null) {
			newNode.setLink(previous);
		}
		
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(String element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
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
	public String getHead() {
		return head.getData();
	}
	
}
