package crypttest;

import static org.junit.Assert.*;


import java.math.BigInteger;

import org.junit.Ignore;
import org.junit.Test;

import cryptography.EncryptionCommunication;
import cryptography.Rsa;

public class TestEncryptionCommunication {
 
  @Test
  public void testEncryptMessage() {		
    EncryptionCommunication ecomm = new EncryptionCommunication();
    String testMessage ="6882326879666680032";
    BigInteger result = BigInteger.ZERO; 
    BigInteger expResult =  new BigInteger("688"); 
 	
    result = ecomm.encryptMessage(testMessage);
    assertEquals(expResult, result);		
  }
  
  @SuppressWarnings("deprecation")
  @Test
  public void testSplitMessageIntoBlocks(){
    EncryptionCommunication ecomm = new EncryptionCommunication();
    String testMessage ="6882326879666680032";
    String[] result = new String[EncryptionCommunication.NUMBER_OF_BLOCKS_THE_MESSAGES_WAS_SPLITTED];
    String[] expResult = new String[EncryptionCommunication.NUMBER_OF_BLOCKS_THE_MESSAGES_WAS_SPLITTED];

    expResult[0] = "688";
    expResult[1] = new String("232");
    expResult[2] = new String("687");
    expResult[3] = new String("966");
    expResult[4] = new String("668");
    expResult[5] = new String("003");
    expResult[6] = new String("2");
    
    result = ecomm.splitMessageIntoBlocks(testMessage);

    assertEquals(expResult, result);			 		 
  }

  @Test
  public void testDecryptAlgorithm(){
    BigInteger testMessage = new BigInteger("1570"); 
    BigInteger expResult = new BigInteger("688");
    
    EncryptionCommunication ecomm = new EncryptionCommunication();
    Rsa rsa = new Rsa();
    rsa.testRsa();
    //System.out.println("n:" + rsa.publicKeyN);
    //System.out.println("e:" + rsa.publicKeyE);
    //System.out.println("d:" + rsa.privateKeyD);
    //System.out.println("testMessage:" + testMessage);
    //System.out.println("result:" + ecomm.decryptAlgorithm(testMessage, rsa));

    assertEquals(expResult, ecomm.decryptAlgorithm(testMessage, rsa));
  }
  
  @Test
  public void testEncryptAlgorithm(){	  
    BigInteger testMessage = new BigInteger("688"); 
    BigInteger expResult = new BigInteger("1570");
    
    EncryptionCommunication ecomm = new EncryptionCommunication();
    Rsa rsa = new Rsa();
    rsa.testRsa();
  
    assertEquals(expResult, ecomm.encryptAlgorithm(testMessage, rsa));
  }
}
