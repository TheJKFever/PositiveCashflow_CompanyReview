package baseClasses;

public interface SortedLLInterface<T> {
	public void add(T element);
	public T get(T element) throws ElementDoesNotExistException;
	public void remove(T element);
	public void clear();
	public boolean contains(T element);
	public String toString();
}
