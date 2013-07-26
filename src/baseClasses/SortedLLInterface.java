package baseClasses;

public interface SortedLLInterface<T> {
	public void add(T element);
	public void remove(T element);
	public void clear();
	public boolean contains(T element);
	public String toString();
}
