package crypto;
/*
 * Class: CMSC203 
 * Instructor: Farnaz Eivazi
 * Description: (isStringInBounds checks to see if all the characters in the input string are within range.
 * 					caesarEncryption encrypts a string by incrementing the ASCII by the given key.
 * 					caesarDecryption decrypts a string by decrementing by the ASCII by the given key.
 * 					bellasoEncryption encrypts a string by incrementing the characters by the ASCII code of each character in a given string
 * 					bellasoDecryption decrypts a string by decrementing the characters by the ASCII code of each character in a given string)
 * Due: 10/20/2022
 * Platform/compiler:Eclipse
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Duga Gang
*/

/**
 * This is a utility class that encrypts and decrypts a phrase using two
 * different approaches. The first approach is called the Caesar Cipher and is a
 * simple “substitution cipher” where characters in a message are replaced by a
 * substitute character. The second approach, due to Giovan Battista Bellaso,
 * uses a key word, where each character in the word specifies the offset for
 * the corresponding character in the message, with the key word wrapping around
 * as needed.
 * 
 * @author Farnaz Eivazi
 * @version 7/16/2022
 */
public class CryptoManager {
	private static final char LOWER_RANGE = ' ';
	private static final char UPPER_RANGE = '_';
	private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_RANGE and UPPER_RANGE characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean isStringInBounds (String plainText) {
		int charCode = 0;
		int length = 0;
		for (int i = 0; i < plainText.length(); i++) 
		{
			charCode = plainText.charAt(i);
			if (charCode <= UPPER_RANGE && charCode >= LOWER_RANGE) 
			{
				length += 1;
			}
			
		}
		if (length == plainText.length()) 
		{
			return true;
		}
		else 
		{
			return false;
		}
		
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String caesarEncryption(String plainText, int key) {

		
		
		String caesarEnc = "";
		int intCode = 0;
		boolean status = true;
		
		 
		for (int i = 0; i < plainText.length(); i++) 
		{
			intCode = plainText.charAt(i);
			
			if (intCode < LOWER_RANGE || intCode > UPPER_RANGE)
			{
				status = false;
			}
			intCode += key;
			
			while(intCode > 96) 
			{
				intCode -= 64;
			}
			caesarEnc += (char)intCode;
			
		}
		
		
		if (status == false) 
		{
			return "The selected string is not in bounds, Try again.";
		}
		else 
		{
			return caesarEnc;
		}
		
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String bellasoEncryption (String plainText, String bellasoStr) {
		String bellasoEnc = "";
		int intCode;
		int key;
		int count = 0;
		
		for(int i = 0; i < plainText.length(); i++) 
		{
			key = bellasoStr.charAt(count);
			intCode = plainText.charAt(i);
			count++;
			
			intCode += key;
			intCode -= 64;
			
			while (intCode <= 33) 
			{
				intCode += 64;
			}
			while (intCode >= 96) 
			{
				intCode -= 64;
			}
			
			bellasoEnc += (char)intCode;
			
			if(count == bellasoStr.length()) 
			{
				count = 0;
			}
		}
		
		return bellasoEnc;
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String caesarDecryption (String encryptedText, int key) {
		String caesarDec = "";
		int intCode = 0;
		boolean status = true;
		
		 
		for (int i = 0; i < encryptedText.length(); i++) 
		{
			intCode = encryptedText.charAt(i);
			
			if (intCode < LOWER_RANGE || intCode > UPPER_RANGE)
			{
				status = false;
			}
			intCode -= key;
			
			while(intCode < 32) 
			{
				intCode += 64;
			}
			caesarDec += (char)intCode;
			
		}
		
		
		if (status == false) 
		{
			return "The selected string is not in bounds, Try again.";
		}
		else 
		{
			return caesarDec;
		}
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String bellasoDecryption(String encryptedText, String bellasoStr) {
		String bellasoDec = "";
		int intCode;
		int key;
		int count = 0;
		
		for(int i = 0; i < encryptedText.length(); i++) 
		{
			key = bellasoStr.charAt(count);
			intCode = encryptedText.charAt(i);
			count++;
			
			intCode -= key;
			intCode += 64;
			
			while (intCode <= 33) 
			{
				intCode += 64;
			}
			while (intCode >= 96) 
			{
				intCode -= 64;
			}
			
			bellasoDec += (char)intCode;
			
			if(count == bellasoStr.length()) 
			{
				count = 0;
			}
		}
		
		return bellasoDec;
	}
}
