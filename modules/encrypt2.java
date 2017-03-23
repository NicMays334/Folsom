import Jama.Matrix;

/**
 * 	@author Nic Mays	
 *	@version linear algebra multiplication char by char cypher
 */

/**
 * 	@TODO Cutdown on code by using JAMA for initial matrix writes
 * 	WORK with MATH.round after decrypting to cut down on precision issues
 * 	rewrite multiplication algorithm for more code and less use of the Jama library
 */

public class encrypt2 
{

	public static void printMatrix(int[][] matrix)
	{
		for(int i=0; i<matrix.length; i++)
		{
			System.out.print("| ");
			for(int j=0; j<matrix[0].length; j++)
			{
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println("|");
		}
	}
	
	public static void printMatrix(char[][] matrix)
	{
		for(int i=0; i<matrix.length; i++)
		{
			System.out.print("| ");
			for(int j=0; j<matrix[0].length; j++)
			{
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println("|");
		}
	}
	
	public static void printMatrix(Matrix matrix)
	{
		for(int i=0; i<matrix.getRowDimension(); i++)
		{
			System.out.print("| ");
			for(int j=0; j<matrix.getColumnDimension(); j++)
			{
				System.out.print(matrix.get(i, j)+" ");
			}
			System.out.println("|");
		}
	}
	
	public static void printMatrix(double[][] matrix)
	{
		for(int i=0; i<matrix.length; i++)
		{
			System.out.print("| ");
			for(int j=0; j<matrix[0].length; j++)
			{
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println("|");
		}
	}
	public static double[][] diagonal(double[][] matrix)
	{
	    int temp=0;

	    for (int i=0; i<matrix[0].length; i++)
	    {
	        for (int j=0; j<matrix[i].length; j++)
	        {
	            if (i<matrix.length-1&&j==matrix[i].length-1)
	            { 
	                temp = (int) matrix[i][j];                       
	                matrix[i][j] = matrix[i+1][0];                       
	                matrix[i+1][0] = temp;                              
	            }                                                      
	        }                                                          
	    }
	    return(matrix);
	}
		
	public static void main(String[] args) 
	{
		String message = "prepare to negotiate";
		String key = "GOD";
		int keyid = 0;
		double[][] encodingMatrix = new double[key.length()][key.length()];
		
		for(int i=0; i<key.length(); i++)
		{
			for(int j=0; j<key.length(); j++)
			{
				encodingMatrix[i][j] = (int) key.charAt(keyid);
				keyid++;
				
				if(keyid>=key.length())
					keyid=0;
			}
		}
		
		encodingMatrix = diagonal(encodingMatrix);
		printMatrix(encodingMatrix);
		System.out.println("\n\n\n\n");
		
		for(int i=0; i<encodingMatrix.length; i++)
		{
			System.out.println();
			for(int j=0; j<encodingMatrix.length; j++)
			{
				System.out.print(encodingMatrix[i][j]+" ");
			}
		}
		
		
		int bound=0;
		System.out.println(message.length()+"+"+key.length());
		//int[][] messageMatrix = new int[message.length()][message.length()];
		if(message.length()%key.length()==0)
			bound=message.length()/key.length();
		else
			bound=(message.length()/key.length())+1;
		System.out.println(bound);
		
		int x = (int) ' ';
		System.out.println(x);
		
		double[][] messageMatrix = new double[key.length()][bound];
		int c = 0;
		
		for(int j=0; j<messageMatrix[0].length; j++)
		{
			for(int i=0; i<messageMatrix.length; i++)
			{
				if(c<message.length())
				{
					messageMatrix[i][j] = (int) message.charAt(c);
					c++;
				}
				
				else
					messageMatrix[i][j]=' ';
			}
		}
		
		printMatrix(encodingMatrix);
		
		Matrix MESSAGE = new Matrix(messageMatrix);
		printMatrix(MESSAGE);
		Matrix ENCODING = new Matrix(encodingMatrix);
		Matrix CYPHER = ENCODING.times(MESSAGE);
		
		printMatrix(CYPHER);
		Matrix DECRYPT = ENCODING.inverse();
		
		MESSAGE = DECRYPT.times(CYPHER);
		printMatrix(MESSAGE);
	}
}
