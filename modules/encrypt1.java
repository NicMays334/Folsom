/**
 * 	@author Nic Mays	
 *	@version char by char Encryption 1: XOR
 */

/**
 * 	@TODO: Return a copy of array for privacy purposes
 */

/*
 * Algorithm:
 * Input: String
 * Encryption: Converts string to binary, XORS the binary with key
 * Output: Long numbers, usually resembling binary for the front half of the number.
 * 
 * Input: int[]
 * Decryption: XORS Long numbers with key. Converts the Long to String.
 * 	Builds string by parses int with a radix of 2 and casting them as chars.
 * Output: Original String. 
 */

public class encrypt1 
{
	//takes a string key and returns it as a summation of its char values
	public static int getNewKey(String key)
	{
		int KeyPrime=0;
		for(int i=0; i<key.length(); i++)
			KeyPrime+=(int)key.charAt(i);
		return(Math.abs(KeyPrime));
	}
	
	public static int[] encrypt(String s, String key)
	{
		
		byte[] bytes = s.getBytes();
		int nums[] = new int[bytes.length];
		int keyPrime = getNewKey(key);
		
		for(int i=0; i<bytes.length; i++)
		{
			nums[i] = Integer.valueOf(Integer.toBinaryString(bytes[i]));
			nums[i] = nums[i]^keyPrime;
		}
		return(nums);
	}
	
	public static String decrypt(int[] nums, String key)
	{
		String solution="";
		String converter;
		int keyPrime = getNewKey(key);
		
		
		for(int i=0; i<nums.length; i++)
		{
			nums[i] = nums[i]^keyPrime;
			converter = Integer.toString(nums[i]);
			solution+=(char)Integer.parseInt(converter, 2);
		}
		return(solution);
	}
	
	public static void main(String[] args) 
	{
		
		String key = "Ezekiel 27:15";
		String message="The path of the righteous man is beset on all sides by the iniquities of the selfish and the tyranny of evil men." +
				"\nBlessed is he who, in the name of charity and good will, shepherds the weak through the valley of darkness, for he is truly his brother's keeper and the finder of lost children." +
				"\nAnd I will strike down upon thee with great vengeance and furious anger those who attempt to poison and destroy my brothers. And you will know my name is the Lord when I lay my vengeance upon thee.";;
		double start;
		double finish;
		int encrypted[];
		
		System.out.println("Key: "+key+"\n");
		System.out.println("Key value: "+getNewKey(key));
		
		start=System.currentTimeMillis()/1000.0;
		encrypted = encrypt(message, key);
		finish=System.currentTimeMillis()/1000.0;
		
		for(int i: encrypted)
			System.out.print(i+", ");
		System.out.println();
		System.out.print("Encrypted in "+(finish-start)+" seconds.\n\n");
		
		message = "";
		start=System.currentTimeMillis()/1000.0;
		message = decrypt(encrypted, key);
		finish=System.currentTimeMillis()/1000.0;
		
		System.out.println(message);
		System.out.print("Decrypted in "+(finish-start)+" seconds.");
	}
}