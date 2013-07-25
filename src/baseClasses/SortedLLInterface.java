package baseClasses;


public interface SortedLLInterface<T> {
	public void insert(T element);
	public void clear();
	public boolean isFull();
	public boolean contains(T element);
	public String toString();
}
