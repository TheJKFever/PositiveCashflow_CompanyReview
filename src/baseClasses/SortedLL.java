package baseClasses;


public class SortedLL<T> implements SortedLLInterface<T>{
	
	LLNode<T> head,previous,current;
	
	public SortedLL() {
		head=null;
		previous=null;
		current=null;
	}
	@Override
	public void insert(T element) {
		// insert beginning	
		LLNode<T> newNode = new LLNode<T>(element);
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
	public T getHead() {
		return head.getData();
	}
	@Override
	public boolean contains(Object element) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
