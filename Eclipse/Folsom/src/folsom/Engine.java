package folsom;

import java.io.IOException;
import java.util.Scanner;
public class Engine
{
	public static void main(String[] args) throws IOException
	{

		Scanner reader = new Scanner(System.in);
		FileReadWrite fwr = new FileReadWrite();
		String filename = "";
		String key = "";
		byte choice = 0;
		System.out.println("Folsom: ");
		System.out.println("\t1. Encrypt text file (.txt)");
		System.out.println("\t2. Decrypt text file (.txt)");
		System.out.print("choice: ");
		choice = reader.nextByte();
		double start;
		double finish;
		
		switch(choice)
		{
			case 1:
			{
				System.out.print("Enter File Name (.txt) ex. Cash.txt: ");
				filename = reader.next();
				System.out.print("\nEnter a key: ");
				key = reader.next();
				
				//reads, encrypts, and writes with time stamp
				start = System.currentTimeMillis();
				TextEncryptor te = new TextEncryptor(fwr.readTxt(filename), key, false);
				te.dynamicEncrypt();
				
				fwr.writeCypher(filename, te.getMessage());
				finish = System.currentTimeMillis();
				
				System.out.println("Successful encryption of "+filename);
				System.out.println("Executed in: "+((finish-start)/1000)+" seconds.");
				reader.close();
				te.terminate();
			}
			break;
			
			case 2:
			{
				System.out.print("Enter File Name (.txt) ex. Cash.txt: ");
				filename = reader.next();
				System.out.print("\nEnter a key: ");
				key = reader.next();
				
				//reads, decrypts and writes with time stamp
				start = System.currentTimeMillis();
				TextEncryptor td = new TextEncryptor(fwr.readCypher(filename), key, true);
				td.dynamicDecrypt();
				
				fwr.writeTxt(filename, td.getMessage());
				finish = System.currentTimeMillis();
				System.out.println("Successful encryption of "+filename);
				System.out.println("Executed in: "+((finish-start)/1000)+" seconds.");
				reader.close();
				
			}
			break;
		}
		
		
	}
}
