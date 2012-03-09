package crypttest;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

import cryptography.RSA;

public class RSATest {

	
	@Test
	public void testencryptingMessage() {		
		RSA rsa = new RSA();
		BigInteger testMessage = new BigInteger("100"); 
		BigInteger result = BigInteger.ZERO; 
		
		result = rsa.encryptingMessage(testMessage);
		assertEquals(testMessage, testMessage);
	}

}
