/**
 * 	@author Nic Mays	
 *	@version matrix diagonal fill
 */

/**
 * 	Builds a matrix diagonally
 */

public class diagonalFill
{
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
		int temp = 0; 

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

		double[][] matrix = new double[3][3];
		int c = 1;
		for(int i=0; i<matrix.length; i++)
			for(int j=0; j<matrix[0].length; j++)
			{
				matrix[i][j] = c;
				c++;
			}

		printMatrix(matrix);

		matrix = diagonal(matrix);

		System.out.println("\n\n");
		printMatrix(matrix);

	}

}
