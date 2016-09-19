import java.io.*;
//import java.util.*;

public class KeywordInputter
{
    String keyword;
	
	public void setter() throws IOException
	{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     System.out.println("Enter the keyword(s):");
	     System.out.println("For multiple keywords, please separate them with a plus sign.");
	     System.out.println("For example: milky+way");
	     System.out.println();
         keyword = br.readLine();   
	}
	
	public String getter()
	{
		return keyword;
	}
}