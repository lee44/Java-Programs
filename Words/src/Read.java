import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Read 
{
static FileReader fileReader;
static BufferedReader bufferedReader;
    
static FileWriter fileWriter; 
static BufferedWriter bufferedWriter;
    
public static void main(String[] args) throws IOException 
{
    String fileRead, fileWrite = "10LetteredWords";
    String line = null;
    
    fileWriter = new FileWriter(fileWrite);
    bufferedWriter = new BufferedWriter(fileWriter);
    
    try 
    {
    	for(int i = 65; i < 91; i ++)
    	{
    		char a = (char) i;
    		fileRead = Character.toString(a) + " Words.txt";
    		fileReader = new FileReader(fileRead);
    		bufferedReader = new BufferedReader(fileReader);
                
    		while((line = bufferedReader.readLine()) != null) 
    		{
    			if(line.length() == 10)
    			{
    				bufferedWriter.write(line);
    				bufferedWriter.newLine();
    			}
    		}
    	}
        bufferedWriter.close();
        bufferedReader.close();        			
    }
    catch(FileNotFoundException ex) 
    {
        System.out.println();				
    }
    catch(IOException ex) 
    {
        System.out.println();	
    }
}
}


