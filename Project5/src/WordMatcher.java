import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class WordMatcher implements IWordMatcher
{
	private final int TABLE_SIZE = 11117; 
	private List<String>[] table = new LinkedList [TABLE_SIZE];
	
	public WordMatcher(String filename)
	{
		try (Scanner in = new Scanner(new File(filename))) 
		{	
			String word="";
			int i=0;
			while (in.hasNextLine()) 
			{
				word=in.nextLine().toLowerCase();
				i=Math.abs(Hashindex(word)) % TABLE_SIZE;
				
					if(table[i]==null)
					{
						table[i]=new LinkedList<String>();
						table[i].add(word);						
					}
					else  
					{
						table[i].add(word);
					}
			}
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();			 
		}
	}
	private static int Hashindex(String s) 
	{		 
		 int hash = 0;
		 int n = s.length();
		 
		 char[] chars = s.toCharArray();
	     Arrays.sort(chars);
		 
		 for (int i = 0; i < n; i++)
			 hash += chars[i] * (int)Math.pow(31, n-(i+1));
		 
		 return hash;
	}
	public List<String> getMatches(String word) 
	{
		List<String> list=new LinkedList<String>();
		List<String> list2=new LinkedList<String>();
		ListIterator<String> it=null;

		int index=Math.abs(Hashindex(word.toLowerCase())) % TABLE_SIZE;
		
		if(table[index]!=null)
    	{
			list2=table[index];
			it=list2.listIterator();
			
			while(it.hasNext())
			{
				String s=it.next();
				char[] chars = word.toLowerCase().toCharArray();
				char[] chars2 = s.toLowerCase().toCharArray();
				
				Arrays.sort(chars);
			    Arrays.sort(chars2);
			    
			    String first=new String(chars);
			    String second=new String(chars2);
				
				if(first.equals(second) && !word.toLowerCase().equals(s.toLowerCase()))
				{
					list.add(s);
				}
			}
			
    	}
		else
			System.out.print("No match found");
		
			
		return list;
	}
	
}
