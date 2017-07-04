import java.util.Iterator;

public class SimpleStack<T> implements ISimpleStack<T>, Iterable<T>
{
	SinglyLinkedList<T> list=new SinglyLinkedList<T>();
	
	@Override
	public boolean isEmpty() 
	{
		return list.size()==0;
	}

	@Override
	public void push(T e) 
	{
		 list.add(e);		
	}

	@Override
	public T pop() 
	{
		return list.remove(list.size()-1);
	}

	@Override
	public T peek() 
	{	
		return list.get(list.size()-1);
	}

	@Override
	public Iterator<T> iterator() 
	{
		return list.iterator();
				 	
	}
}
