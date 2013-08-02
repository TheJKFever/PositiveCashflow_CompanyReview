package baseClasses;

import java.io.Serializable;

/**
 * @author Jeroen Goossens & Jon Koehmstedt
 * CSC202 Final Project
 */
public class LLNode<T> implements Comparable<LLNode<T>>, Serializable {

	private static final long serialVersionUID = -4333566258263636472L;
	private LLNode<T> link;
	private T data;

	public LLNode(T info) {
		this.data = info;
		link = null;
	}
	
	//GETTERS
	public LLNode<T> getLink() {
		return link;
	}

	public T getData() {
		return data;
	}
	
	//SETTERS
	public void setData(T info) {
		this.data = info;
	}

	public void setLink(LLNode<T> link) {
		this.link = link;
	}	
	
	@SuppressWarnings("unchecked")
	public int compareTo(LLNode<T> other) {
		return ((Comparable<T>)this.getData()).compareTo(other.getData());
	}

}
 
 