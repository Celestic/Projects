package symmetricLocker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class SymmetricCryptoLocker {

	protected String msg;
	protected static Cipher cipher = null;

	public static final String KEY_FILE = "C:/CryptoLocker/Symmetric/keys/private.key";
	public static final String MSG_ENCRYPTION = "C:/CryptoLocker/Symmetric/Encryption/Output.txt";

	protected byte[] msgBytes;
	protected byte[] cipherBytes;
	protected byte[] decryptBytes;

	public SymmetricCryptoLocker(String msg) throws Exception {
		this.msg = msg;

		cipher = Cipher.getInstance("AES");

	}

	/**
	 * Encrypt a message with a symmetric algorithm and write the output to a
	 * file
	 * 
	 * @param secretKey
	 * @throws Exception
	 */
	public void SymmetricEncrypt(SecretKey secretKey) throws Exception {
		msgBytes = msg.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		cipherBytes = cipher.doFinal(msgBytes);
		String cipherText = new String(cipherBytes, "UTF-8");
		System.out.println("Encrypted Message: " + cipherText);

//		PrintWriter writer = new PrintWriter("Output.txt", "UTF-8");
//		writer.println(cipherText);
//		writer.close();
//		
		File file = new File(MSG_ENCRYPTION);

		// Create files to store public and private key
		if (file.getParentFile() != null) {
			file.getParentFile().mkdirs();
		}
		file.createNewFile();
		
		ObjectOutputStream msgOS = new ObjectOutputStream(new FileOutputStream(
				MSG_ENCRYPTION));
		msgOS.writeObject(secretKey.toString());
		msgOS.close();

	}

	/**
	 * Decrypt a message with a symmetric algorithm
	 * 
	 * @param secretKey
	 * @throws Exception
	 */
	public void SymmetricDecrypt(SecretKey secretKey) throws Exception {

		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		decryptBytes = cipher.doFinal(cipherBytes);
		String decryptedText = new String(decryptBytes, "UTF-8");
		System.out.println("Decrypted Message: " + decryptedText);
	}

	/**
	 * Generate a key
	 * 
	 * @return
	 * @throws Exception
	 */
	public SecretKey generateKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		SecretKey secretKey = keyGenerator.generateKey();

		File file = new File(KEY_FILE);

		// Create files to store key
		if (file.getParentFile() != null) {
			file.getParentFile().mkdirs();
		}
		file.createNewFile();
		
		ObjectOutputStream keyOS = new ObjectOutputStream(new FileOutputStream(
				KEY_FILE));
		keyOS.writeObject(secretKey.toString());
		keyOS.close();

		return secretKey;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
