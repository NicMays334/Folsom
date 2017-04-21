package folsom;

import java.io.*;
import java.util.Scanner;
public class FileReadWrite
{	
	/**
	 * @param filename
	 * @return String of message contents in .txt file
	 */
	public String readTxt(String filename) 
	{
		File file = new File(filename);
		String inputText = "";
		if(!file.exists())
			System.err.println("Bad File Name");
		
		try
		{
			Scanner scanner = new Scanner(file);
			inputText= scanner.useDelimiter("\\Z").next();
			scanner.close();
			
		}catch (FileNotFoundException e) 
		{
			System.err.println("Bad File Name");
		}
		
		return inputText;
	}
	
	/**
	 * @param filename
	 * @param text
	 * @throws IOException
	 * 
	 * Writes .txt message to a file
	 */
	public void writeTxt(String filename, String text) throws IOException
	{
		FileOutputStream out = null;
		File file = new File(filename);
		
		try 
		{
			out = new FileOutputStream("outagain.txt");
			PrintWriter pw = new PrintWriter(file);
			pw.println(text);
			pw.close();
			out.close();
		}catch (FileNotFoundException e) 
		{
			System.err.println("Bad File Name");
		}
	}
	
	/**
	 * @param filename
	 * @return String of Cypher in the form "### ### #### #### ### "
	 */
	public String readCypher(String filename)
	{
		String token = "";
		try{
			FileInputStream iStream = new FileInputStream(filename); 
			InputStreamReader iStrReader = new InputStreamReader(iStream);
			BufferedReader bufReader = new BufferedReader(iStrReader);
			StreamTokenizer reader = new StreamTokenizer(bufReader);
			reader.nextToken();
			while(reader.ttype != StreamTokenizer.TT_EOF)
			{
				token+=(int)reader.nval+" ";
				reader.nextToken();
			}
			iStream.close();

		}catch (IOException e) 
		{
			System.err.println("Bad file name");
			return "";
		}
		return token;
	}
	
	/**
	 * @param filename
	 * @param cypher
	 * @throws IOException
	 * 
	 * writes a cypher to a text file in the form
	 * 1. ###
	 * 2. ###
	 * 3. ###
	 */
	public void writeCypher(String filename, String cypher) throws IOException
	{
		FileOutputStream out = null;
		File file = new File(filename);
		String[] numArray = cypher.split(" ");
		String s = "";
		
		try 
		{
			out = new FileOutputStream(filename);
			PrintWriter pw = new PrintWriter(file);
			for(int i=0; i<numArray.length; i++)
			{
				s=numArray[i];
				s=s.trim();
				pw.println(s);
			}
			pw.close();
			out.close();
		}catch (FileNotFoundException e) 
		{
			System.err.println("Bad File Name");
		}
	}
}