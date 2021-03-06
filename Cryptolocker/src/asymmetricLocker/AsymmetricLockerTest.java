package asymmetricLocker;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;


public class AsymmetricLockerTest {

	static AsymmetricCryptoLocker test = new AsymmetricCryptoLocker();
	static Scanner input;
	static File file = new File("");
	
	public static void main(String[] args) {
		input = new Scanner(System.in);
		
		try {

			// Check if the pair of keys are present else generate those.
			if (!test.areKeysPresent()) {
				// Method generates a pair of keys using the RSA algorithm and
				// stores it
				// in their respective files
				test.generateKey();
			}
			System.out.println("Text to encrypt: ");
			String originalText = input.nextLine();
			ObjectInputStream inputStream = null;
			
			
			// Encrypt the string using the public key
			inputStream = new ObjectInputStream(new FileInputStream(
					test.PUBLIC_KEY_FILE));
			final PublicKey publicKey = (PublicKey) inputStream.readObject();
			final byte[] cipherText = test.encrypt(originalText, publicKey);
			
			
			
			// Decrypt the cipher text using the private key.
			inputStream = new ObjectInputStream(new FileInputStream(
					test.PRIVATE_KEY_FILE));
			final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
			final String plainText = test.decrypt(cipherText, privateKey);

			// Printing the Original, Encrypted and Decrypted Text
			System.out.println("Original Text: " + originalText);
			System.out.println("Encrypted Text: " + cipherText.toString());
			System.out.println("Decrypted Text: " + plainText);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
