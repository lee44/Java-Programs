import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ParenthesesChecker 
{
	public static boolean checkParentheses(String filename) 
	{
		ISimpleStack<String> stack = new SimpleStack<String>();
		String delimiters = "()";
		
		try (Scanner in = new Scanner(new File(filename))) 
		{			
			while (in.hasNextLine()) 
			{
				StringTokenizer st = new StringTokenizer(in.nextLine(), delimiters, true);
				
				while (st.hasMoreTokens()) 
				{					
					String token = st.nextToken();
					
					if(token.equals("("))
						stack.push(token);
					else 
						if(token.equals(")"))
							if(stack.isEmpty())
								return false;
							else 
								if(stack.peek().equals("("))
									stack.pop();				
				}				
			}	
			if(!stack.isEmpty())
				return false;
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean checkParenthesesExtra(String filename)
	{
		ISimpleStack<String> stack = new SimpleStack<String>();
		String delimiters = "[]{}()";
		
		try (Scanner in = new Scanner(new File(filename))) 
		{			
			while (in.hasNextLine()) 
			{
				StringTokenizer st = new StringTokenizer(in.nextLine(), delimiters, true);
				
				while (st.hasMoreTokens()) 
				{					
					String token = st.nextToken();
					
					if(token.equals("(") || token.equals("[") || token.equals("{"))
						stack.push(token);
					else if(token.equals(")"))
					{
						if(stack.isEmpty())
							return false;
						else 
							if(stack.peek().equals("("))
								stack.pop();
					}
								
					else if(token.equals("]"))
					{
						if(stack.isEmpty())
							return false;
						else 
							if(stack.peek().equals("["))
								stack.pop();
					}
					else if(token.equals("}"))
					{
						if(stack.isEmpty())
							return false;
						else 
							if(stack.peek().equals("{"))
								stack.pop();
					}
					
				}
			}
						
			if(!stack.isEmpty())
				return false;	
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return true;
	}
}
