import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * @author Joshua Lee
 * @SMC_ID 1372646
 * 
 * CS 20B Programming Project 1
 *
 */

public class SMCAlumniFinder 
{

	/**
	 * Read in a file containing student records and create an array of Student
	 * objects
	 * 
	 * @param file The filename
	 * @param num The number of students in the file
	 * @return An array of Student objects
	 */
	
	static Student[] readStudentsFromFile(String filename, int num) 
	{
		try (Scanner in = new Scanner(new File(filename))) 
		{
			Student[] students = new Student[num];
			String line,id,name,school;
			int ident;
			StringTokenizer t;
			
			for(int i=0;i<num;i++)
			{
				line=in.nextLine();
				t=new StringTokenizer(line,",");
				name=t.nextToken();
				id=t.nextToken();
				ident=Integer.parseInt(id);
				school=t.nextToken();
				
				students[i]=new Student(ident,name,school);
				
				//System.out.print(students[i].toString());
			}
			 //students[i]=new Student();
			
			// YOUR CODE HERE
			// Hints:
			// To read a line from the file you can call 
			//		String line = in.nextLine();
			// The split method of the String class might come in handy to get the fields
			
			return students;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Write an array of Student objects to a file
	 * 
	 * @param students The array of Student objects to write out
	 * @param filename The output filename
	 */
	static void writeStudentsToFile(Student[] students, String filename) 
	{
		try (FileWriter out = new FileWriter(filename)) 
		{
			for(int i=0;i<students.length;i++)
			out.write(students[i].toString());
			
			// YOUR CODE HERE
			// Hints:
			// To write a line to the file you can call 
			//		   out.write("Hello World!" + "\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find students belonging to both groups.
	 * 
	 * This function checks each student in group1 for membership in group2 by
	 * comparing it with every student in group2.
	 * 
	 * @param group1 A group of Students
	 * @param group2 A group of Students
	 * @param numCommon number of students belonging to both groups
	 * @return An array of Students which belong to both group1 and group2
	 */
	static Student[] findCommonStudents1(Student[] group1, Student[] group2,int numCommon) 
	{
		Student[] common = new Student[numCommon];
		int a=0;
		
		
		// YOUR CODE HERE
		for(int p=0;p<group1.length;p++)
		{
			
			for(int q=0;q<group2.length;q++)
			{	
				
				if(group1[p].equals((group2[q])))
				{
					common[a]=group1[p];
					a++;
				}	
			}
		}
		return common;
	}

	/**											Binary Search
	 * Find students belonging to both groups.
	 * 
	 * This function checks each student in group1 for membership in group2 by
	 * doing a binary search on group2.
	 * 
	 * @param group1 A group of Students
	 * @param group2 A group of Students
	 * @param numCommon number of students belonging to both groups
	 * @return An array of Students which belong to both group1 and group2
	 */
	static Student[] findCommonStudents2(Student[] group1, Student[] group2,int numCommon) 
	{
		Student[] common = new Student[numCommon];
		Arrays.sort(group2);
		int t=0,index;
		for(int i=0;i<group1.length;i++)
		{
			if((index=Arrays.binarySearch(group2,group1[i]))>=0)
			{
				common[t]=group2[index];
				t++;
			}
		}
		
		// YOUR CODE HERE
		// Hints:
		// To sort an array of Comparables you can call    
		//		Arrays.sort(arr);
		// To search for an element in a sorted array you can call
		// 		Arrays.binarySearch(arr, element)
		// if the returned index is >= 0, the element was found at that index

		return common;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/***** These files provided to help you with initial testing *****/
		
		//Student[] uc = readStudentsFromFile("sample_uc_students.txt", 10);
		//Student[] smc = readStudentsFromFile("sample_smc_grads.txt", 5);
		//final int SMC_UC_GradsNumber = 2;
		
		/***** Use these files for the output you submit *****/
		Student[] uc = readStudentsFromFile("uc_students.txt", 350000);
		Student[] smc = readStudentsFromFile("smc_grads.txt", 75000);
		final int SMC_UC_GradsNumber = 25000;

		
		long start, end;
		/*
		start = System.currentTimeMillis();
		Student[] common1 = findCommonStudents1(uc, smc, SMC_UC_GradsNumber);
		end = System.currentTimeMillis();
		System.out.println("Cross checking took " + (end - start) / 1000.0+ " seconds.");
		Arrays.sort(common1);
		writeStudentsToFile(common1, "test.txt");
		*/
		start = System.currentTimeMillis();
		Student[] common2 = findCommonStudents2(uc, smc, SMC_UC_GradsNumber);
		end = System.currentTimeMillis();
		System.out.println("Using binary search it took " + (end - start)/ 1000.0 + " seconds.");
		Arrays.sort(common2);
		writeStudentsToFile(common2, "smc_grads_at_uc_2.txt");	
		
	}
}
//Part 4
/*
 * Yes, because in a binary search the approach is similiar to what is called "divide and conquer." It repeatedly divides the amount of
 * things to search by half in order to find the Object key. As a result, the less amount of things the computer has to divide to search, the faster 
 * the performance is. For example, the program above is .064 seconds faster then doing a binary search the other way.
 * 
 * 
 * */

