package symmetricLocker;

import java.util.Scanner;

import javax.crypto.SecretKey;

public class SymmetricCryptoLockerTest {
	
	static SymmetricCryptoLocker locker;
	static Scanner input;
	
	
	public static void main(String [] args) throws Exception{
		String msg;
		
		input = new Scanner(System.in);
		System.out.println("Message to Encrypt");
		msg = input.nextLine();
		
		locker = new SymmetricCryptoLocker(msg);
		SecretKey secretKey = locker.generateKey();
		locker.SymmetricEncrypt(secretKey);
		locker.SymmetricDecrypt(secretKey);
	}
}
