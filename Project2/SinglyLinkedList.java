import java.util.Iterator;

public class SinglyLinkedList<T> implements ISimpleList<T>, Iterable<T>
{
	private Node front;
	private Node back;
	private int size;
	
	private class Node 
	{
		T value;
		Node next;
	}

	@Override
	public int size() 
	{
		return size;
	}

	@Override
	public void add(T e) 
	{
		Node n = new Node();
		n.value = e;
		
		if (front == null) 
		{ 
			front = n;
			back = n;
		} 
		else 
		{
			back.next = n;
			back = n;
		}
		size++;
	}

	@Override
	public T get(int index) 
	{
		Node q=front; 
		
		if(index==0)
			return front.value;
		else
		{
			for(int i=0;i<index;i++)
				q=q.next;
		}
		return q.value;
	}

	@Override
	public void set(int index, T element) 
	{
		 Node q=front;
		 
		 if(index==0)
			 q.value=element;
		 else
		 {
			 for(int i=0;i<index;i++)
				 q=q.next;
		 }
		 q.value=element;
	}

	@Override
	public void add(int index, T element) 
	{
		Node q=front;
		Node n= new Node();
		
		if(index==0)
		{ 
			n.next=front;
			front=n;
			n.value=element;
			
			size++;
		}
		else				
		{
			for(int i=0;i<index-1;i++)
				q=q.next;
			
			n.next=q.next;
			q.next=n;
			n.value=element;
			
			size++;
		}
		 	
	}

	@Override
	public T remove(int index) 
	{
		Node q=front,n=front;
		T val;
		
		if(index==0)
		{
			val = front.value;
			front = n.next;
			size--;
		}
		else				
		{
			for(int i=0;i<index;i++)
				q=q.next;
			
			val=q.value;
			
			for(int i=0;i<index-1;i++)
				n=n.next;
				
				n.next=n.next.next;
						
			size--;
		}
			
		return val;	
	}

	private class MyListIterator implements Iterator<T> 
	{
		Node cur = front;
		
		@Override
		public boolean hasNext() 
		{
			return cur != null;
		}
		
		@Override
		public T next() 
		{
			T val = cur.value;
			cur = cur.next;
			
			return val;
		}
		
		@Override
		public void remove() {}
	}
	@Override
	public Iterator<T> iterator() 
	{
		return new MyListIterator();
	}
	
}

	 
