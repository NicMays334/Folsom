package folsom;

/**
 * 	@author Nic Mays	
 *	@version 1.0
 *	
 *	TODO Picture, GUI
 */
public class TextEncryptor 
{
	private String message;
	private String key;
	private boolean encrypted;

	/**
	 * No Arg Constructor. Defaults are empty Strings
	 */
	TextEncryptor()
	{
		this.message="";
		this.key="";
		this.encrypted = false;
	}

	/**
	 * @param message full text stored in a single string
	 * @param key used for encryption 
	 * @param encrypted is the text already encrypted or not with dynamic encryption
	 */
	TextEncryptor(String message, String key, boolean encrypted)
	{
		this.message=message;
		this.key=key;
		this.encrypted = encrypted;
	}

	/**
	 * Accessor method for message
	 * @return message String value of the objects message
	 */
	public String getMessage()
	{
		return(this.message);
	}

	/**
	 * Mutator method for message
	 * @param newMessage Message to be set to
	 */
	public void setMessage(String newMessage)
	{
		this.message=newMessage;
	}

	/**
	 * Accessor method for key
	 * @return key String value of the object's key
	 */
	public String getKey()
	{
		return(this.key);
	}

	/**
	 * Mutator method for key
	 * @param newKey Key to be set to
	 */
	public void setKey(String newKey)
	{
		this.key=newKey;
	}

	/**
	 * Accessor method for Encrypted
	 * @return true/false if the message is ecnrypted or not
	 */
	public boolean isEncrypted()
	{
		return(this.encrypted);
	}

	/**
	 * Generates a random key of A-Z, a-z, and 0-9
	 * Key is the length of the message or 10 characters, whichever the lesser.
	 */
	public void generateKey()
	{
		String newKey = "";
		int max = 0;
		int range = 0;
		int nextChar = 0;
		
		//handles the max length of the key
		if(this.message.length()<=10)
			max = this.message.length();
		else
			max = 10;
		
		//System.out.println((int)'a');
		for(int i=0; i<max; i++)
		{
			//evaluates what range to use 1,2, or 3
			range = (int) (Math.random()*3 + 1);
			
			//range 1: 0-9
			if(range==1)
			{
				nextChar = 48 + (int)(Math.random()*((57-48)+1));
				newKey+= (char) nextChar;
			}
			
			//range 2: A-Z
			else if(range==2)
			{
				nextChar = 65 + (int)(Math.random()*((90-65)+1));
				newKey+= (char) nextChar;
			}
			
			//range 3: a-z
			else
			{
				nextChar = 97 + (int)(Math.random()*((122-97)+1));
				newKey+= (char) nextChar;
			}
		}
		this.key=newKey;
	}
	
	/**
	 * Dynamically Encrypts the message's text
	 * with a specific key
	 */
	public void dynamicEncrypt()
	{
		String cypher = "";
		byte[] bytes = this.message.getBytes();
		int nums[] = new int[bytes.length];
		int keyID = 0;

		//encrypting
		for(int i=0; i<bytes.length; i++)
		{
			//conversion
			nums[i] = Integer.valueOf(Integer.toBinaryString(bytes[i]));
			
			//xor
			nums[i] = nums[i]^(int)this.key.charAt(keyID);
			keyID++;
			if(keyID>=this.key.length())
				keyID=0;
		}
		
		//builds a cypher string
		for(int i=0; i<nums.length; i++)
			cypher+=Integer.toString(nums[i])+" ";

		cypher=cypher.trim();
		this.message=cypher;
		this.encrypted=true;
	}

	/**
	 * Dynamically Decrypts the message's already encrypted text
	 */
	public void dynamicDecrypt()
	{
		//splits the message at white space. Stored in array
		String[] parser = this.message.split(" ");
		int[] nums = new int[parser.length];

		//unpacks string values to Integers.
		for(int i=0; i<parser.length; i++)
			nums[i] = Integer.valueOf(parser[i]); 

		String solution="";
		String converter;
		int keyID = 0;

		//decrypting
		for(int i=0; i<nums.length; i++)
		{
			//xor
			nums[i] = nums[i]^(int)this.key.charAt(keyID);
			keyID++;
			if(keyID>=this.key.length())
				keyID=0;
			
			//conversion
			converter = Integer.toString(nums[i]);
			solution+=(char)Integer.parseInt(converter, 2);
		}

		this.message=solution;
		this.encrypted=false;
	}

	/**
	 * Security Protocol
	 * Eliminates all traces of messages and keys
	 */
	public void terminate()
	{
		this.message=null;
		this.key=null;
		this.encrypted=false;
		System.gc();
	}
	
}
