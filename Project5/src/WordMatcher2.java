import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;

public class WordMatcher2 implements IWordMatcher
{
    static Map<String, List<String>> map = new HashMap<String, List<String>>();
    
    public WordMatcher2(String filename)
	{
		try (Scanner in = new Scanner(new File(filename))) 
		{	
			String s="";
			while (in.hasNextLine()) 
			{
				s=in.nextLine().toLowerCase();
				String sorted = sort(s);
                List<String> words = map.get(sorted);
                if (words == null) 
                {
                	words=new LinkedList<String>();
                    words.add(s);
                    map.put(sorted, words);
                }
                else
                	words.add(s);
            }
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();			 
		}
	}
	
    private static String sort(String s) 
    {
    	char[] chars = s.toLowerCase().toCharArray();
    	Arrays.sort(chars);
        return new String(chars);
    }

    public List<String> getMatches(String word) 
    {
    	List<String>words=new LinkedList<String>();
    	List<String>words2=null;
    	ListIterator<String> ite=null;
    	
    	if(map.get(sort(word))!=null)
    	{
    	    words2 = map.get(sort(word));
    	    ite=words2.listIterator();
    	    
    	    while(ite.hasNext())
    	    {
    	    	String s=ite.next();
    	    	if(!word.toLowerCase().equals(s.toLowerCase()))
				{
					words.add(s);
				}
    	    }
    	}
    	else 
        	System.out.println("No match found");
		
    	return words;	    
    }
}

