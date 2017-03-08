import java.io.*;

public class fileReadWrite
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		FileInputStream stream;
		int choice = 1;
		switch(choice)
		{
			case 1:
			{
				try 
				{
					FileOutputStream oStream = new FileOutputStream("Million"+".txt");
					oStream.close();
					
				} catch (IOException e) 
					{
						// TODO Auto-generated catch block
						System.err.println("Bad file name");
					}
			}
			break;
			
			case 2:
			{
				try 
				{
					FileOutputStream oStream = new FileOutputStream("5HunnaThou"+".txt");
					PrintWriter printWriter = new PrintWriter (oStream, true);
					for (int i=1;i<=500000;i++)
						printWriter.println((int)(20*Math.random()+1));
					oStream.close();
					
				} catch (IOException e) 
					{
						// TODO Auto-generated catch block
						System.err.println("Bad file name");
					}
			}
			break;
			
			case 3:
			{
				try 
				{
					FileInputStream iStream = new FileInputStream("Million.txt"); 
					InputStreamReader iStrReader = new InputStreamReader(iStream);
					BufferedReader bufReader = new BufferedReader(iStrReader);
					StreamTokenizer reader = new StreamTokenizer(bufReader);
					String token ="";
					reader.nextToken();
					while(reader.ttype != StreamTokenizer.TT_EOF)
					{
						token = (int)reader.nval +"";
						System.out.print(token+", ");
						reader.nextToken();
					}
					iStream.close();
					
				} catch (IOException e) 
					{
						// TODO Auto-generated catch block
						System.err.println("Bad file name");
					}
			}
			break;
			
		}
	}
}
