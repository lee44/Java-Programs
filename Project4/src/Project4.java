import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * 
 * @author Joshua Lee
 * @SMC_ID 1372646
 * 
 * CS 20B Programming Project 4
 *
 */

public class Project4 
{
	
	// ****************************************************************
	/**
	 * DO NOT MODIFY THE NODE CLASS
	 */
	static class Node 
	{
		Node(int data) 
		{
			this.data = data;
		}

		int data;
		Node left;
		Node right;
	}	
	// ****************************************************************
	
	/*
	 * Part 1 - a
	 */
	static int longestPath(Node root) 
	{
		if (root==null) 
			return 0;
		else
		{
			int left = longestPath(root.left);
		    int right = longestPath(root.right);
		 
		    if (left > right) 
		    	return left+1;
		    else 
		    	return right+1;
		   }
	}
	
	/*
	 * Part 1 - b
	 */
	static void setSize(Node root) 
	{	     	        	
		if(root==null)
	        return;
		
		root.data=1;
		
		setSize(root.left);
		if(root.left!=null)
			root.data+=root.left.data;
		
		setSize(root.right);	
		if(root.right!=null)
			root.data+=root.right.data;  
	   }
	/*
	 * Part 1 - c
	 */
	static void flip(Node root) 
	{
		if(root==null)
	        return;
		else
		{
			Node temp= root.left;
		    root.left=root.right;
		    root.right=temp;
		  
		    flip(root.left);
		    flip(root.right);
	  
		    return;
	   }
	}
	
	
	/*
	 * Part 2
	 */
	static void printFrequentNames(String filename, int threshold) 
	{
		Map<String, Integer> map= new TreeMap<String,Integer>();
		
		try (Scanner in = new Scanner(new File(filename))) 
		{		
			while (in.hasNextLine()) 
			{
				StringTokenizer st = new StringTokenizer(in.nextLine(), " ", true);
				String str=st.nextToken();
								
				if(str.length()>0)
					if(!map.containsKey(str))
					{
						map.put(str, 1);
					}
					else
					{
						int value=map.get(str);
						value++;
						map.put(str, value);
					}
			}
			Set<Map.Entry<String, Integer>> entrySet=map.entrySet();
			
			for(Map.Entry<String, Integer>entry:entrySet)
			{
				if(entry.getValue()>=threshold)
					System.out.println(entry.getKey()+" "+entry.getValue());
			}
			
			 
			
			
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();			 
		}
	
	}
	
	public static void main(String args[])
	{
		Node n=new Node(1);
		n.left=new Node(2);
		n.right=new Node(3);
		n.left.left=new Node(4);
		//n.left.right=new Node(5);
		//n.right.left=new Node(6);
		//n.right.right=new Node(6);
		
		printFrequentNames("students.txt",0);
	}
}
