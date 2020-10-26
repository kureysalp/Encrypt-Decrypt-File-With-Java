package HM1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random; 

public class Program {

	public static void main(String[] args) {
		
		Encryption("C:\\ExampleFolder", "FileToRead.txt", "EncryptedFile.txt");
		
		Decryption("C:\\ExampleFolder", "EncryptedFile.txt", "DecryptedFile.txt");	
	}
	
	static void Encryption(String path, String fileToRead, String fileToWrite) 
	{
		File _file = new File(path + "\\" + fileToRead); // Read the file.
		
		byte[] _fileBytes = new byte[(int) _file.length()]; // Declare a byte array with size of the file.
		
		FileInputStream fis = null; // Declare a file input stream.
		
		try {
			fis = new FileInputStream(_file); // Create a new file input stream with given path.
			fis.read(_fileBytes); // Read the file to bytes. 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} 
		
		if(fis != null)
			try {
				fis.close(); // Close the stream.
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		
		
		byte[] _newFileBytes = new byte[(int) _fileBytes.length * 2]; // Create new byte array to write decrypted file.
		
		for(int i = 0; i < _fileBytes.length; i++)
		{
			// Generate a random number to flip that digit
			Random _rand = new Random();
			int _randomBit = _rand.nextInt(8);
						
			_newFileBytes[i * 2] = (byte) (_fileBytes[i] ^ 1 << _randomBit); // Flip the '_randomBit' th digit of byte with bitshifting and XOR. 
			_newFileBytes[i * 2 + 1] = (byte) _randomBit; // Save the '_randomBit' to decrypt the file later.
		}
		
		FileOutputStream fos = null; // Declare a file output stream.
		
		try {
			fos = new FileOutputStream(path + "\\" +  fileToWrite); // Create a file output stream with given path.
			fos.write(_newFileBytes); // Save the decrypted file.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} 
	}
	
	static void Decryption(String path, String fileToRead, String fileToWrite)
	{
		File _file = new File(path + "\\" +  fileToRead); // Read the file.
		
		byte[] _fileBytes = new byte[(int) _file.length()]; // Declare a byte array with size of the file.
		
		FileInputStream fis = null; // Declare a file input stream.
		
		try {
			fis = new FileInputStream(_file); // Create a new file input stream with given path.
			fis.read(_fileBytes); // Read the file to bytes. 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		byte[] _newFileBytes = new byte[(int) _fileBytes.length / 2]; // Create new byte array to write encrypted file.
		
		for(int i = 0; i < _newFileBytes.length; i++)
		{					
			_newFileBytes[i] = (byte) (_fileBytes[i * 2] ^ 1 << _fileBytes[i * 2 + 1]); // Flip the bytes digit back with bitshifting and XOR. Digits saved at next index of real data bytes.
		}
		
		FileOutputStream fos = null; // Declare a file output stream.
		
		try {
			fos = new FileOutputStream(path + "\\" + fileToWrite); // Create a file output stream with given path.
			fos.write(_newFileBytes); // Save the decrypted file.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} 
	}

}
