import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * 
 * @author Joshua Lee
 * @SMC_ID 1372646
 * 
 * CS 20B Programming Project 3
 *
 */

public class StableSorting 
{
	
	/**
	 * Sorts students by school. Uses Java library's merge sort 
	 * @param students array already sorted by ID
	 */
	public static void sortByGroupById1(Student[] students) 
	{
		Arrays.sort(students, new SortStudents());
	}
	
	/**
	 * You can fill this out and use it if you find it helpful for sortByGroupById2. 
	 *
	 * Return the array index for a school (schools in alphabetical order)
	 * "UCB", "UCD", "UCI", "UCLA", "UCM", "UCSD", "UCSF"
	 *   0		 1	    2	     3	      4      5        6
	 * @param school
	 * @return the index to use for the bucket
	 */
	private static int schoolToIndex(String school) 
	{
		// Hint: a switch statement is a good fit
		if(school.equals("UCB"))
			return 0;
		else if(school.equals("UCD"))
			return 1;
		else if(school.equals("UCI"))
			return 2;
		else if(school.equals("UCLA"))
			return 3;
		else if(school.equals("UCM"))
			return 4;
		else if(school.equals("UCSD"))
			return 5;
		else if(school.equals("UCSF"))
			return 6;
		
		return -1;
	}
	
	/**
	 * Sorts students by school. An implementation of counting sort. 
	 * @param students array already sorted by ID
	 */
	public static void sortByGroupById2(Student[] students) 
	{
		final int NUM_SCHOOLS = 7;
		int[] counts = new int[NUM_SCHOOLS];
		Student[]s=new Student[students.length];
		
	    
		for(int i=0;i<students.length;i++)
		{
			int c=schoolToIndex(students[i].getSchool());
			counts[c]+=1;
		}
		
		for(int i=1;i<counts.length;i++)
		{
			counts[i]+=counts[i-1];
		}
		for(int z=students.length-1;z>0;z--)
		{
			 s[counts[schoolToIndex(students[z].getSchool())]-1] = students[z];
			 counts[schoolToIndex(students[z].getSchool())] = counts[schoolToIndex(students[z].getSchool())] - 1;  
		}
		System.arraycopy(s, 0, students, 0, s.length);
	}
	
	static class SortStudents implements Comparator<Student>
	{
		@Override
		public int compare(Student s1, Student s2) 
		{
			String school1 = s1.getSchool();
		    String school2 = s2.getSchool();
		    
		    return school2.compareTo(school1);
		}
		
	}
	
}
